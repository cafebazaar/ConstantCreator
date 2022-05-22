package com.farsitel.bazaar.sample

import com.farsitel.bazaar.constantcreator.annotation.HasEnumConstant



@HasEnumConstant("ViewHolderConsts", hasValue = true, nameSuffix = "_VIEW_TYPE")
annotation class ViewHolderEnum

@ViewHolderEnum
class SampleViewHolder {

//    val viewType : ViewHolderConsts = ViewHolderConsts.SampleViewHolder_VIEW_TYPE
}
