package com.farsitel.bazaar.constantcreator.enum

import com.farsitel.bazaar.constantcreator.annotation.HasEnumConstant
import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.visitor.KSEmptyVisitor
import com.squareup.kotlinpoet.TypeSpec

internal class AnnotatedClassVisitor : KSEmptyVisitor<EnumClass, Unit>() {

    @OptIn(KspExperimental::class)
    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: EnumClass) {
        return data.enumBuilder.addEnumConstants(data.hasEnumConstant, classDeclaration)
    }

    override fun defaultHandler(node: KSNode, data: EnumClass) = Unit

    private fun TypeSpec.Builder.addEnumConstants(hasEnumConstant: HasEnumConstant, classDeclaration: KSClassDeclaration) {
        with(hasEnumConstant) {
            val constName = classDeclaration.simpleName.asString() + nameSuffix
            addEnumConstant(constName, TypeSpec.anonymousClassBuilder().apply {
                if (hasValue) {
                    addSuperclassConstructorParameter("%L", getCounterFor(enumClassName))
                }
            }.build())
        }
    }
}

private var counterMap = mutableMapOf<String, Int>()

private fun getCounterFor(className: String): Int {
    var counter = counterMap[className] ?: 0
    counterMap[className] = ++counter
    return counter
}
