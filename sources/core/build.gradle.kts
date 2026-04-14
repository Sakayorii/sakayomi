plugins {
    id("com.android.library")
    id("Sakayorii.lint")
}

android {
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        minSdk = AndroidConfig.minSdk
    }

    namespace = "Sakayorii.core"

    buildFeatures {
        resValues = false
        shaders = false
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-opt-in=kotlinx.serialization.ExperimentalSerializationApi")
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}

dependencies {
    compileOnly(versionCatalogs.named("libs").findBundle("common").get())
}
