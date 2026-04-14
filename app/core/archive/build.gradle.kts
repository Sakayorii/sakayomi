plugins {
    alias(sakayomix.plugins.android.library)
    alias(sakayomix.plugins.spotless)

    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "sakayomi.core.archive"
}

dependencies {
    implementation(libs.jsoup)
    implementation(libs.archive)
    implementation(libs.unifile)
}
