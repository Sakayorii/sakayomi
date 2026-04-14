import sakayomi.gradle.Config

plugins {
    alias(sakayomix.plugins.android.library)
    alias(sakayomix.plugins.spotless)
}

android {
    namespace = "sakayomi.telemetry"

    sourceSets {
        getByName("main") {
            if (Config.includeTelemetry) {
                kotlin.srcDirs("src/firebase/kotlin")
            } else {
                kotlin.srcDirs("src/noop/kotlin")
                manifest.srcFile("src/noop/AndroidManifext.xml")
            }
        }
    }
}

dependencies {
    implementation(projects.core.common)

    if (Config.includeTelemetry) {
        implementation(platform(libs.firebase.bom))
        implementation(libs.firebase.analytics)
        implementation(libs.firebase.crashlytics)
    }
}
