package com.farsitel.bazaar.constantcreator.constant

import com.farsitel.bazaar.constantcreator.annotation.HasConstant
import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getAnnotationsByType
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.visitor.KSEmptyVisitor
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec

internal class AnnotatedClassVisitor(
    private val generationHelper: GenerationHelper,
    private val logger: KSPLogger
) : KSEmptyVisitor<String, Unit>() {

    @OptIn(KspExperimental::class)
    override fun visitClassDeclaration(
        classDeclaration: KSClassDeclaration,
        data: String
    ) {
        classDeclaration.getAnnotationsByType(HasConstant::class).forEach {
            generationHelper.fileBuilder.addConstant(
                hasConstant = it,
                classDeclaration,
                projectPath = data,
                valueGenerator = generationHelper::getValueFor
            )
        }
    }

    override fun defaultHandler(node: KSNode, data: String) = Unit

    private inline fun FileSpec.Builder.addConstant(
        hasConstant: HasConstant,
        classDeclaration: KSClassDeclaration,
        projectPath: String,
        valueGenerator: (String) -> Int
    ) {
        val constName = classDeclaration.simpleName.asString() + hasConstant.nameSuffix
        val uniqueClassName = projectPath + classDeclaration.qualifiedName!!.asString()
        val value = valueGenerator(uniqueClassName)
        logger.logging("uniqueClassName: $uniqueClassName - value: $value")
        addProperty(
            PropertySpec.builder(constName, Int::class, KModifier.CONST)
                .initializer("%L", value)
                .build()
        )
        addProperty( // Temporary solution until getConstantValue equivalent implemented in ksp
            PropertySpec.builder(value.toString(), Int::class, KModifier.CONST, KModifier.PRIVATE)
                .initializer("%L", value)
                .build()
        )
    }
}
