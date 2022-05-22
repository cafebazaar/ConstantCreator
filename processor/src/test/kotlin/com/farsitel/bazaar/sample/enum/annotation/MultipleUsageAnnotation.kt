package com.farsitel.bazaar.sample.enum.annotation

import com.farsitel.bazaar.constantcreator.annotation.HasEnumConstant

@HasEnumConstant(enumClassName = "MultipleUsage", hasValue = true)
annotation class MultipleUsageAnnotation

@HasEnumConstant(enumClassName = "SecondMultipleUsage", hasValue = true)
annotation class SecondMultipleUsageAnnotation
