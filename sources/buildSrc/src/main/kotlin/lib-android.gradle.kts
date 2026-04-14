plugins {
    id("com.android.library")
    id("kotlinx-serialization")
    id("Sakayorii.lint")
}

android {
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        minSdk = AndroidConfig.minSdk
    }

    namespace = "Sakayorii.lib.${project.name}"

    sourceSets {
        named("main") {
            java.directories.clear()
            java.directories.add("src")
            kotlin.directories.clear()
            kotlin.directories.add("src")
            assets.directories.clear()
            assets.directories.add("assets")
        }
    }

    androidResources.enable = false
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-opt-in=kotlinx.serialization.ExperimentalSerializationApi")
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}

dependencies {
    compileOnly(versionCatalogs.named("libs").findBundle("common").get())
    implementation(project(":core"))
}
