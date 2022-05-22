package com.farsitel.bazaar.constantcreator.annotation

@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.SOURCE)
@Repeatable
annotation class HasEnumConstant(
    val enumClassName: String,
    val nameSuffix: String = _CONST, // AnnotatedClassNamenameSuffix
    val hasValue: Boolean = false,
    val valueName: String = VALUE
)

@Suppress("ObjectPropertyName")
private const val _CONST = "_CONST"
internal const val VALUE = "value"
