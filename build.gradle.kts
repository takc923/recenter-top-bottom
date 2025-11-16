import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java")
    kotlin("jvm") version "2.1.21"
    id("org.jetbrains.intellij.platform") version "2.10.4"
}

group = "io.github.takc923"
version = "0.4-SNAPSHOT"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        intellijIdeaCommunity("2025.2.1")
    }
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

intellijPlatform {
    pluginConfiguration {
        id = "io.github.takc923.recenter-top-bottom"
        name = "recenter-top-bottom"
        version = project.version.toString()
        vendor {
            name = "takc923"
            url = "https://github.com/takc923"
        }
        ideaVersion {
            sinceBuild = "252"
        }
        description = """
            <p>This plugin scroll like recenter-top-bottom of emacs.</p>
            <p>Default keymap is C-l</p>
        """.trimIndent()
        changeNotes = """
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
    }

    pluginVerification {
        ides {
            recommended()
        }
    }
}
