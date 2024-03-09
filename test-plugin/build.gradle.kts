plugins {
    id("xyz.jpenilla.run-paper") version "2.2.2"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("java")
}

group = "de.tamion.test-plugin"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    implementation(project(":"))
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    runServer {
        minecraftVersion("1.20.4")
    }
}