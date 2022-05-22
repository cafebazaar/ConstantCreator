val kspVersion: String by project

plugins {
    kotlin("jvm")
    id("maven-publish")
}

group = "com.farsitel.bazaar.constant-creator.processor"
version = "0.1"

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.squareup:kotlinpoet-ksp:1.11.0")
    implementation("com.google.devtools.ksp:symbol-processing-api:$kspVersion")
    implementation(project(":library"))

    testImplementation("junit:junit:4.13.2")
    testImplementation("com.github.tschuchortdev:kotlin-compile-testing-ksp:1.4.8")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group as String
            version = project.version as String

            from(components["java"])
        }
    }
}
