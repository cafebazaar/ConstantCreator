plugins {
    id("com.google.devtools.ksp")
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    api(project(":sample"))
    implementation(project(":annotations"))
    ksp(project(":processor"))
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
    sourceSets.test {
        kotlin.srcDir("build/generated/ksp/test/kotlin")
    }
}

ksp {
    arg("projectPath", project.path)
}
