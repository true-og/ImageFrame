plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "ImageFrame"

include(
    "abstraction",
    "V1_19_4",
    "common",
)
