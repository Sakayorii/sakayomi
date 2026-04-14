plugins {
    alias(sakayomix.plugins.android.library)
    alias(sakayomix.plugins.spotless)

    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "tachiyomi.core.metadata"
}

dependencies {
    implementation(projects.sourceApi)

    implementation(libs.bundles.serialization)
}
