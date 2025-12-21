plugins {
    id("java")
    id("maven-publish")
}

group = "org.jpc"
version = "3.0.4-ven"

java.toolchain.languageVersion.set(JavaLanguageVersion.of(8))

java {
    withSourcesJar()
}

// Change the args here if you want to do a custom run
val defArgs = listOf("-fda", "mem:resources/images/floppy.img", "-hda", "mem:resources/images/dosgames.img", "-boot", "fda")

tasks.register<JavaExec>("runJPCApplication") {
    group = "jpc"
    dependsOn("classes")
    classpath = java.sourceSets["main"].runtimeClasspath

    mainClass = "org.jpc.j2se.JPCApplication"
    args = defArgs
}

tasks.register<JavaExec>("runJPCDebugger") {
    group = "jpc"
    dependsOn("classes")
    classpath = java.sourceSets["main"].runtimeClasspath

    mainClass = "org.jpc.debugger.JPC"
    args = defArgs
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