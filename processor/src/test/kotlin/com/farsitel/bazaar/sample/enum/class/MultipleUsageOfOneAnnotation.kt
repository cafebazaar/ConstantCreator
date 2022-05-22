package com.farsitel.bazaar.sample.enum.`class`

import com.farsitel.bazaar.sample.enum.annotation.MultipleUsageAnnotation
import com.farsitel.bazaar.sample.enum.annotation.SecondMultipleUsageAnnotation

@MultipleUsageAnnotation
class First {
}

@SecondMultipleUsageAnnotation
class One {
}

@MultipleUsageAnnotation
class Second {
}

@SecondMultipleUsageAnnotation
class Two {
}

@SecondMultipleUsageAnnotation
class Three {
}

@MultipleUsageAnnotation
class Third {
}
