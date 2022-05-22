package com.farsitel.bazaar.constantcreator.enum

import com.farsitel.bazaar.constantcreator.annotation.HasEnumConstant
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec

internal class EnumClass(
    val fileBuilder: FileSpec.Builder, val enumBuilder: TypeSpec.Builder, val hasEnumConstant: HasEnumConstant
)
