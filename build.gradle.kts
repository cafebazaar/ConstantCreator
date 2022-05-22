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
}
