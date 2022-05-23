# ConstantCreator, Generate Unique Constants For your Classes!

Welcome to ConstantCreator!

ConstantCreator is a KSP-based library that can generate <b>Unique Int constants</b>
from your classes that are annotated with `@HasConstant`.

# Usage

First, annotate your class with `@HasConstant`.

Let's see it in action:

```kotlin 

@HasConstant
class SampleClass

// Can change nameSuffix of generated constant.
@HasConstant(nameSuffix = "_ViewType")
class SampleViewHolder

// HasConstant can be repeated to generate multiple constants.
@HasConstant(nameSuffix = "_DataConst")
@HasConstant(nameSuffix = "_ViewConst")
class MyAdapter(val data: Data, val view: View)

```

The code above will generate the following code:

```kotlin
val SampleClass_CONST = 1292182827
val SampleViewHolder_ViewType = 232312827
val MyAdapter_DataConst = -987012921
val MyAdapter_ViewConst = 65756129218

```

You can change constant name suffix with `nameSuffix` parameter in `@HasConstant`(Defaults to _CONST).

You can safely use the generated constants, anywhere.

```kotlin 

class SomeAdapter {

   override fun getViewType() : Int = SampleViewHolder_ViewType
}

```

# Installation

Add the following to your build.gradle (or build.gradle.kts)

```groovy
plugins {
    id 'com.google.devtools.ksp' version '1.6.21-1.0.5'
    // the version according to your Kotlin and KSP
}

// to add generated files support for IDE
android {
    kotlin {
        sourceSets.debug {
            kotlin.srcDirs += 'build/generated/ksp/debug/kotlin'
        }
        sourceSets.release {
            kotlin.srcDirs += 'build/generated/ksp/release/kotlin'
        }
    }
}

dependencies {
    implementation "com.github.cafebazaar.ConstantCreator:annotations:{latest_version}"
    ksp "com.github.cafebazaar.ConstantCreator:processor:{latest_version}"
}

```

## Feedback and Bug Reporting

[Please let me know what you think about this project by filing a Github issue](https://github.com/cafebazaar/ConstantCreator/issues)

## Contribution

Any type of contribution includes bug fixes and adding new features are welcomed :) .
