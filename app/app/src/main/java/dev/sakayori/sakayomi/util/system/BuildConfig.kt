@file:Suppress("UNUSED", "KotlinConstantConditions")

package dev.sakayori.sakayomi.util.system

import dev.sakayori.sakayomi.BuildConfig

val telemetryIncluded: Boolean
    inline get() = BuildConfig.TELEMETRY_INCLUDED

val updaterEnabled: Boolean
    inline get() = BuildConfig.UPDATER_ENABLED

val isDebugBuildType: Boolean
    inline get() = BuildConfig.BUILD_TYPE == "debug"

val isPreviewBuildType: Boolean
    inline get() = BuildConfig.BUILD_TYPE == "preview"

val isReleaseBuildType: Boolean
    inline get() = BuildConfig.BUILD_TYPE == "release"

val isFossBuildType: Boolean
    inline get() = BuildConfig.BUILD_TYPE == "foss"
