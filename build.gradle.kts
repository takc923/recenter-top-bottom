plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
    id("org.jetbrains.intellij") version "1.17.2"
}

group = "io.github.takc923"
version = "0.4-SNAPSHOT"

repositories {
    mavenCentral()
}

intellij {
    version.set("2023.1")
    updateSinceUntilBuild.set(false)
    pluginName.set("recenter-top-bottom")
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
    patchPluginXml {
        sinceBuild.set("231")
        changeNotes.set(
            """
            <p>v0.3</p>
            <ul>
              <li>Fix weired keyboard shortcut settings.</li>
              <li>Update dependencies.</li>
            </ul>
            <p>v0.2</p>
            <ul>
              <li>Update kotlin and supported intellij version</li>
            </ul>
            <p>v0.1</p>
            <ul>
              <li>Initial release</li>
            </ul>
            """.trimIndent()
        )
    }
}