package sakayomi.gradle.configurations

import com.android.build.api.dsl.ApplicationDefaultConfig
import sakayomi.gradle.extensions.android
import sakayomi.gradle.extensions.coreLibraryDesugaring
import sakayomi.gradle.extensions.libs
import sakayomi.gradle.extensions.sakayomix
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.configureAndroid() {
    android {
        defaultConfig {
            minSdk = sakayomix.versions.android.sdk.min.get().toInt()
            if (this is ApplicationDefaultConfig) {
                targetSdk = sakayomix.versions.android.sdk.target.get().toInt()
            }

            ndkVersion = sakayomix.versions.android.ndk.get()
        }

        compileSdk = sakayomix.versions.android.sdk.compile.get().toInt()

        compileOptions {
            isCoreLibraryDesugaringEnabled = true
        }
    }

    dependencies {
        coreLibraryDesugaring(libs.android.desugar)
    }
}
