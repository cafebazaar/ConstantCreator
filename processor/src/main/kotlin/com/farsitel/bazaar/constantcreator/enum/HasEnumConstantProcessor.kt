package com.farsitel.bazaar.constantcreator.enum

import com.farsitel.bazaar.constantcreator.annotation.HasEnumConstant
import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.ksp.KotlinPoetKspPreview
import com.squareup.kotlinpoet.ksp.writeTo

class HasEnumConstantProcessor(
    private val codeGenerator: CodeGenerator, private val logger: KSPLogger
) : SymbolProcessor {

    @OptIn(KotlinPoetKspPreview::class, KspExperimental::class)
    override fun process(resolver: Resolver): List<KSAnnotated> {
        logger.logging("classes: " + resolver.getAllFiles().joinToString { it.fileName })

        val hasEnumConstantAnnotations = resolver.getSymbolsWithAnnotation(
            HasEnumConstant::class.qualifiedName!!
        ).filterIsInstance<KSClassDeclaration>() // Annotations are KSClassDeclaration
            .apply {
                logger.logging("hasEnumConstantAnnotationsForCurrentModule: " + joinToString { it.qualifiedName!!.asString() })
            }

        val remainingAnnotated = mutableListOf<KSAnnotated>()
        hasEnumConstantAnnotations.filter { it.validate() }.forEach { annotation ->

            val annotatedSymbols = resolver.getSymbolsWithAnnotation(
                annotation.qualifiedName!!.asString()
            ).partition { isValid(it) }.run {
                remainingAnnotated.addAll(second)
                if (first.isEmpty()) {
                    return@forEach
                }
                first
            }

            val enumClasses = annotation.accept(AnnotationVisitor(), Unit)
            enumClasses.forEach {
                val visitor = AnnotatedClassVisitor()
                annotatedSymbols.forEach { annotatedSymbol ->
                    annotatedSymbol.accept(visitor, it)
                }

                it.fileBuilder.addType(it.enumBuilder.build())
                it.fileBuilder.build().writeTo(codeGenerator, aggregating = true)
                logger.logging(it.fileBuilder.build().name + " generated")
                println(it.fileBuilder.build())
            }
        }
        return remainingAnnotated + hasEnumConstantAnnotations.filter { it.validate().not() }.toList()
    }

    private fun isValid(annotated: KSAnnotated): Boolean {
        return annotated.validate { _, declaration -> declaration is KSClassDeclaration }
    }
}
