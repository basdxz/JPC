plugins {
    id("java")
    id("maven-publish")
}

group = "org.jpc"
version = "3.0.3-ven"

java.toolchain.languageVersion.set(JavaLanguageVersion.of(8))

java {
    withSourcesJar()
}

sourceSets.main {

}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "org.jpc"
            artifactId = "jpc"
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "venmaven"
            setUrl("https://mvn.ventooth.com/releases")
            credentials(PasswordCredentials::class)
        }
    }
}