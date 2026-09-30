plugins {
    id("multiloader-common")
    kotlin("jvm") version "2.0.21"
}

dependencies {
    compileOnly("org.spongepowered:mixin:0.8.5")
    compileOnly("io.github.llamalad7:mixinextras-common:0.3.5")
    annotationProcessor("io.github.llamalad7:mixinextras-common:0.3.5")

    implementation(kotlin("reflect"))
}

tasks.named("compileKotlin") {
    enabled = false
}
tasks.named("compileJava") {
    enabled = false
}
tasks.matching { it.name.startsWith("compileTest") || it.name == "test" || it.name == "jar" || it.name == "sourcesJar" || it.name == "javadocJar" }.configureEach {
    enabled = false
}

configurations {
    create("commonJava") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }
    create("commonResources") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }
}

artifacts {
    add("commonJava", sourceSets["main"].java.sourceDirectories.singleFile)
    add("commonResources", sourceSets["main"].resources.sourceDirectories.singleFile)
}
