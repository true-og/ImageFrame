import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.attributes.java.TargetJvmVersion
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.bundling.AbstractArchiveTask
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.jvm.toolchain.JvmVendorSpec
import org.gradle.jvm.tasks.Jar

plugins {
    id("java")
    id("com.gradleup.shadow") version "8.3.9" apply false
    eclipse
}

group = "com.loohp"
version = "1.9.0.3"

val adventureVersion = "4.25.0"
val moduleMinecraftVersions =
    mapOf(
        "V1_16" to "1.16.1-R0.1-SNAPSHOT",
        "V1_16_2" to "1.16.3-R0.1-SNAPSHOT",
        "V1_16_4" to "1.16.4-R0.1-SNAPSHOT",
        "V1_17" to "1.17.1-R0.1-SNAPSHOT",
        "V1_18" to "1.18.1-R0.1-SNAPSHOT",
        "V1_18_2" to "1.18.2-R0.1-SNAPSHOT",
        "V1_19" to "1.19.2-R0.1-SNAPSHOT",
        "V1_19_3" to "1.19.3-R0.1-SNAPSHOT",
        "V1_19_4" to "1.19.4-R0.1-SNAPSHOT",
        "V1_20" to "1.20.1-R0.1-SNAPSHOT",
        "V1_20_2" to "1.20.2-R0.1-SNAPSHOT",
        "V1_20_3" to "1.20.3-R0.1-SNAPSHOT",
        "V1_20_5" to "1.20.5-R0.1-SNAPSHOT",
        "V1_20_6" to "1.20.6-R0.1-SNAPSHOT",
        "V1_21" to "1.21-R0.1-SNAPSHOT",
        "V1_21_1" to "1.21.1-R0.1-SNAPSHOT",
        "V1_21_2" to "1.21.2-R0.1-SNAPSHOT",
        "V1_21_3" to "1.21.3-R0.1-SNAPSHOT",
        "V1_21_4" to "1.21.4-R0.1-SNAPSHOT",
        "V1_21_5" to "1.21.5-R0.1-SNAPSHOT",
        "V1_21_6" to "1.21.6-R0.1-SNAPSHOT",
        "V1_21_7" to "1.21.7-R0.1-SNAPSHOT",
        "V1_21_8" to "1.21.8-R0.1-SNAPSHOT",
        "V1_21_9" to "1.21.9-R0.1-SNAPSHOT",
        "V1_21_10" to "1.21.10-R0.1-SNAPSHOT",
        "V1_21_11" to "1.21.11-R0.1-SNAPSHOT",
    )

allprojects {
    group = rootProject.group
    version = rootProject.version
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "eclipse")

    repositories {
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
        maven { url = uri("file://${System.getProperty("user.home")}/.m2/repository") }
        System.getProperty("SELF_MAVEN_LOCAL_REPO")?.let { path ->
            val dir = file(path)
            if (dir.isDirectory) {
                maven { url = uri("file://${dir.absolutePath}") }
            } else {
                logger.warn("SELF_MAVEN_LOCAL_REPO is not a directory: $path")
            }
        }
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") {
            content {
                includeGroup("org.spigotmc")
                includeGroup("org.bukkit")
                includeGroup("com.mojang")
            }
        }
        maven("https://repo.papermc.io/repository/maven-public/") {
            content {
                includeGroup("dev.folia")
                includeGroup("io.papermc.paper")
            }
        }
        maven("https://repository.liferay.com/nexus/content/repositories/public/") {
            content {
                includeGroup("me.carleslc.Simple-YAML")
            }
        }
        maven("https://repo.viaversion.com") {
            content {
                includeGroup("com.viaversion")
            }
        }
        maven("https://repo.loohpjames.com/repository") {
            content {
                includeGroup("com.loohp")
            }
        }
        maven("https://repo.extendedclip.com/releases/") {
            content {
                includeGroup("me.clip")
            }
        }
    }

    configurations.configureEach {
        if (isCanBeResolved) {
            attributes.attribute(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, 17)
        }
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
            vendor.set(JvmVendorSpec.GRAAL_VM)
        }
        withSourcesJar()
    }

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.isFork = true
        options.compilerArgs.add("-Xlint:deprecation")
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }

    tasks.withType<AbstractArchiveTask>().configureEach {
        isPreserveFileTimestamps = false
        isReproducibleFileOrder = true
    }

    tasks.withType<Jar>().configureEach {
        archiveBaseName.set(project.name)
    }
}

project(":abstraction") {
    dependencies {
        compileOnly("org.spigotmc:spigot-api:1.16.1-R0.1-SNAPSHOT")
        compileOnly("org.bukkit:craftbukkit:1.16.1-R0.1-SNAPSHOT")
        compileOnly("net.kyori:adventure-text-serializer-gson:$adventureVersion")
        compileOnly("net.kyori:adventure-text-serializer-legacy:$adventureVersion")
        compileOnly("net.kyori:adventure-text-serializer-plain:$adventureVersion")
        compileOnly("net.kyori:adventure-api:$adventureVersion")
    }
}

moduleMinecraftVersions.forEach { (moduleName, minecraftVersion) ->
    project(":$moduleName") {
        dependencies {
            compileOnly("org.spigotmc:spigot-api:$minecraftVersion")
            compileOnly("org.bukkit:craftbukkit:$minecraftVersion")
            compileOnly("net.kyori:adventure-text-serializer-gson:$adventureVersion")
            compileOnly("net.kyori:adventure-text-serializer-legacy:$adventureVersion")
            compileOnly("net.kyori:adventure-text-serializer-plain:$adventureVersion")
            compileOnly("net.kyori:adventure-api:$adventureVersion")
            compileOnly(project(":abstraction"))
        }
    }
}

project(":common") {
    apply(plugin = "com.gradleup.shadow")

    dependencies {
        implementation("com.zaxxer:HikariCP:7.0.2")
        implementation("com.loohp:PlatformScheduler:1.0.0")
        compileOnly("com.viaversion:viaversion-api:4.5.1")
        implementation("me.carleslc.Simple-YAML:Simple-Yaml:1.8.1")
        implementation("commons-fileupload:commons-fileupload:1.4")
        compileOnly("me.clip:placeholderapi:2.11.6")
        implementation("net.kyori:adventure-text-serializer-gson:$adventureVersion")
        implementation("net.kyori:adventure-text-serializer-legacy:$adventureVersion")
        implementation("net.kyori:adventure-text-serializer-plain:$adventureVersion")
        implementation("net.kyori:adventure-api:$adventureVersion")
        implementation("com.madgag:animated-gif-lib:1.4")
        compileOnly("org.spigotmc:spigot-api:1.19.4-R0.1-SNAPSHOT")
        implementation("com.twelvemonkeys.imageio:imageio-webp:3.10.1")

        implementation(project(":abstraction"))
        moduleMinecraftVersions.keys.forEach { implementation(project(":$it")) }
    }

    tasks.named<ProcessResources>("processResources") {
        filteringCharset = "UTF-8"
        filesMatching("plugin.yml") {
            filter { line -> line.replace("\${project.version}", project.version.toString()) }
        }
        from(rootProject.file("LICENSE")) {
            into("/")
        }
    }

    tasks.named<Jar>("jar") {
        archiveClassifier.set("part")
    }

    tasks.named<ShadowJar>("shadowJar") {
        archiveClassifier.set("")
        relocate("com.loohp.platformscheduler", "com.loohp.imageframe.libs.com.loohp.platformscheduler")
        relocate("com.zaxxer.hikari", "com.loohp.imageframe.libs.com.zaxxer.hikari")
        relocate("org.simpleyaml", "com.loohp.imageframe.libs.org.simpleyaml")
        relocate("org.yaml.snakeyaml", "com.loohp.imageframe.libs.org.yaml.snakeyaml")
        relocate("com.madgag.gif", "com.loohp.imageframe.libs.com.madgag.gif")
        relocate("net.kyori", "com.loohp.imageframe.libs.net.kyori")
        relocate("org.apache.commons", "com.loohp.imageframe.libs.org.apache.commons")
    }

    tasks.named("build") {
        dependsOn(tasks.named("shadowJar"))
    }
}

tasks.named<Jar>("jar") {
    enabled = false
}

tasks.register<Copy>("collectPluginJar") {
    dependsOn(":common:shadowJar")
    from(project(":common").tasks.named<ShadowJar>("shadowJar").flatMap { it.archiveFile })
    into(layout.buildDirectory.dir("libs"))
}

tasks.named("assemble") {
    dependsOn("collectPluginJar")
}

tasks.named("build") {
    dependsOn("collectPluginJar")
}
