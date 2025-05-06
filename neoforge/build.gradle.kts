plugins {
    id("com.github.johnrengelman.shadow")
}

architectury {
    platformSetupLoomIde()
    neoForge()
}

val common: Configuration by configurations.creating {
    isCanBeResolved = true
    isCanBeConsumed = false

    configurations.getByName("compileClasspath").extendsFrom(this)
    configurations.getByName("runtimeClasspath").extendsFrom(this)
    configurations.getByName("developmentNeoForge").extendsFrom(this)
}

// Files in this configuration will be bundled into your mod using the Shadow plugin.
// Don't use the `shadow` configuration from the plugin itself as it's meant for excluding files.
val shadowBundle: Configuration by configurations.creating {
    isCanBeResolved = true
    isCanBeConsumed = false
}

repositories {
    maven(url = "https://maven.neoforged.net/releases")
}

dependencies {
    neoForge("net.neoforged:neoforge:${rootProject.properties["neoforge_version"]!!}")

    common(project(path = ":common", configuration = "namedElements")) {
        isTransitive = false
    }
    shadowBundle(project(path = ":common", configuration = "transformProductionNeoForge"))
}

tasks.processResources {
    val props = mapOf(
        "version" to project.version
    )
    inputs.properties(props)

    filesMatching("META-INF/neoforge.mods.toml") {
        expand(props)
    }
}

tasks.shadowJar {
    configurations = listOf(shadowBundle)
    archiveClassifier = "dev-shadow"
}

tasks.remapJar {
    inputFile.set(tasks.shadowJar.get().archiveFile)
}