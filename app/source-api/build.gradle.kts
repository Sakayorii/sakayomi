import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(sakayomix.plugins.kotlin.multiplatform)
    alias(sakayomix.plugins.spotless)

    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    android {
        namespace = "dev.sakayori.sakayomi.source"

        defaultConfig {
            consumerProguardFile("consumer-proguard.pro")
        }
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    @Suppress("UnstableApiUsage")
    dependencies {
        api(libs.kotlinx.serialization.json)
        api(libs.injekt)
        api(libs.rxJava)
        api(libs.jsoup)

        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.compose.runtime)
    }

    sourceSets {
        androidMain {
            dependencies {
                implementation(projects.core.common)
                api(libs.androidx.preference)
            }
        }
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}
