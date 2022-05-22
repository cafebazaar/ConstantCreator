package com.farsitel.bazaar.constantcreator.constant

import com.farsitel.bazaar.constantcreator.LIBRARY_PACKAGE_NAME
import com.farsitel.bazaar.constantcreator.annotation.HasConstant
import com.farsitel.bazaar.constantcreator.constant.GenerationHelper.Companion.createGenerationHelper
import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.Modifier
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.ksp.KotlinPoetKspPreview
import com.squareup.kotlinpoet.ksp.writeTo

class HasConstantProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
    private val projectPath: String
) : SymbolProcessor {

    @OptIn(KotlinPoetKspPreview::class)
    override fun process(resolver: Resolver): List<KSAnnotated> {
        logger.logging("classes: " + resolver.getAllFiles().joinToString { it.fileName })

        val hasConstantClasses = resolver.getSymbolsWithAnnotation(
            HasConstant::class.qualifiedName!!
        ).filterIsInstance<KSClassDeclaration>()
            .apply {
                logger.logging("hasConstantClasses: " + joinToString { it.qualifiedName!!.asString() })
            }

        if (hasConstantClasses.iterator().hasNext().not()) {
            return emptyList()
        }
        val constantsFromDependencies = getConstantsFromDependencies(resolver)
        val generationHelper = createGenerationHelper(constantsFromDependencies, projectPath)
        val visitor = AnnotatedClassVisitor(generationHelper, logger)
        hasConstantClasses.filter { it.validate() }.forEach { classDeclaration ->
            classDeclaration.accept(visitor, projectPath)
        }
        val fileSpec = generationHelper.fileBuilder.build()
        fileSpec.writeTo(codeGenerator, aggregating = true)
        logger.logging(
            fileSpec.name + " generated:\n" +
                "$fileSpec"
        )

        return hasConstantClasses.filter { it.validate().not() }.toList()
    }

    @OptIn(KspExperimental::class)
    private fun getConstantsFromDependencies(
        resolver: Resolver
    ): Set<Int> {
        val constantsFromDependencies = resolver
            .getDeclarationsFromPackage(LIBRARY_PACKAGE_NAME)
            .filterIsInstance<KSPropertyDeclaration>()
            .filter { it.modifiers.contains(Modifier.PRIVATE) }

            .map { // Temporary solution until getConstantValue equivalent implemented in ksp
                it.simpleName.asString().toInt()
            }
            .toSet() // To remove duplicate values
            .apply {
                logger.logging("constantsFromDependencies: " + joinToString { it.toString() })
            }
        return constantsFromDependencies
    }
}
