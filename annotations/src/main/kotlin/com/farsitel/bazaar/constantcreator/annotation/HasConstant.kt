package com.farsitel.bazaar.constantcreator.annotation

import java.lang.annotation.Inherited

@Repeatable
@Inherited
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class HasConstant(
    val nameSuffix: String = _CONST, // AnnotatedClassNamenameSuffix
)

@Suppress("ObjectPropertyName")
private const val _CONST = "_CONST"
