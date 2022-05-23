plugins {
    kotlin("jvm")
    `maven-publish`
}

group = "com.farsitel.bazaar.constant-creator.annotations"
version = "0.1"

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group as String
            version = project.version as String
            artifactId = "annotations"

            from(components["java"])
        }
    }
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }
}
