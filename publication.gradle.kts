apply(plugin = "maven-publish")

configure<PublishingExtension> {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group as String
            version = project.version as String

            from(components["java"])
        }
    }
}
