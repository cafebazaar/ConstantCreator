package com.farsitel.bazaar.sample.enum.annotation

import com.farsitel.bazaar.constantcreator.annotation.HasEnumConstant

@HasEnumConstant(enumClassName = "TestEnum")
annotation class TestSampleAnnotation

@HasEnumConstant(enumClassName = "TestEnumHasValue", hasValue = true)
annotation class TestSampleAnnotationHasValue

@HasEnumConstant(enumClassName = "TestEnumHasValueWithValueName", hasValue = true, valueName = "type")
annotation class TestSampleAnnotationHasValueWithValueName

@HasEnumConstant(enumClassName = "TestEnumWithNameSuffix", nameSuffix = "_NAME_SUFFIX")
annotation class TestSampleAnnotationWithNameSuffix
