package com.farsitel.bazaar.sample

import com.farsitel.bazaar.constantcreator.annotation.HasConstant

@HasConstant
open class NeedConstBase {
}

class NeedConstImpl: NeedConstBase() {
}

@HasConstant(nameSuffix = "_SUFFIX")
class NeedConstWithNameSuffix

@HasConstant(nameSuffix = "_FIRST")
@HasConstant(nameSuffix = "_SECOND")
class NeedConstRepeated
