val kspVersion: String by project

plugins {
    kotlin("jvm")
}

group = "com.farsitel.bazaar.constant-creator.processor"
version = "0.1"

java {
    withJavadocJar()
    withSourcesJar()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.squareup:kotlinpoet-ksp:1.11.0")
    implementation("com.google.devtools.ksp:symbol-processing-api:$kspVersion")
    implementation(project(":library"))

    testImplementation("junit:junit:4.13.2")
    testImplementation("com.github.tschuchortdev:kotlin-compile-testing-ksp:1.4.8")
}
