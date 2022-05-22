plugins {
    kotlin("jvm")
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    if (plugins.hasPlugin("maven-publish")) {
        java {
            withSourcesJar()
            withJavadocJar()
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
            kotlinOptions.jvmTarget = "1.8"
            kotlinOptions.jvmTarget = "1.8"
    }
}
