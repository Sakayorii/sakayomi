dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    versionCatalogs {
        create("libs") {
            from(files("../libs.versions.toml"))
        }
        create("sakayomix") {
            from(files("../sakayomi.versions.toml"))
        }
    }
}

rootProject.name = "build-logic"
