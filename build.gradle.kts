plugins {
    id("fabric-loom") version "1.10-SNAPSHOT" apply false
    // see https://projects.neoforged.net/neoforged/moddevgradle for new versions
    id("net.neoforged.moddev") version "0.1.110" apply false
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "2.0.21"
}

tasks.matching { it.name == "jar" || it.name == "sourcesJar" || it.name == "javadocJar" }.configureEach {
    enabled = false
}

repositories {
    mavenCentral()
}


subprojects {
    plugins.apply("org.jetbrains.kotlin.jvm")
    plugins.apply("org.jetbrains.kotlin.plugin.serialization")

    dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
        implementation(kotlin("reflect"))
    }

    tasks.withType<Javadoc>().configureEach {
        enabled = false
    }

    // Loader specific
    if (path != ":common") {
        tasks.withType<JavaCompile> {
            source(project(":common").sourceSets.main.get().allSource)
        }

        tasks.withType<ProcessResources> {
            from(project(":common").sourceSets.main.get().resources)
        }
    }
}