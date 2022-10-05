import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    id("java")
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.codemc.io/repository/maven-snapshots/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    implementation("com.github.retrooper.packetevents:spigot:2.0.0-SNAPSHOT")
}

tasks {
    shadowJar {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("")

        val pkg = "com.comugamers.quboblocker.lib"
        relocate("com.github.retrooper.packetevents", "$pkg.packetevents")
        relocate("io.github.retrooper.packetevents", "$pkg.packetevents")
    }

    named<DefaultTask>("build") {
        dependsOn("shadowJar")
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(8))
        }
    }

    compileJava {
        options.compilerArgs.add("-parameters")
    }
}

bukkit {
    val projectName = "${findProperty("plugin-name")}"
    load = BukkitPluginDescription.PluginLoadOrder.POSTWORLD
    main = "com.comugamers.quboblocker.QuboBlocker"
    apiVersion = "1.13"
    version = "${project.version}"
    authors = listOf("Pabszto")
    description = "${findProperty("plugin-description")}"
    softDepend = listOf("ProtocolLib", "ProtocolSupport", "ViaVersion", "ViaBackwards", "ViaRewind", "Geyser-Spigot")
    name = projectName
}