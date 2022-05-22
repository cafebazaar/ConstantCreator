package com.farsitel.bazaar.sample.constant

import com.farsitel.bazaar.constantcreator.annotation.HasConstant

@HasConstant
class SimpleTestClass {
}

@HasConstant(nameSuffix = "_Suffix")
class SimpleTestClassWithNameSuffix {
}
