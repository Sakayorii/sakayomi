import sakayomi.gradle.extensions.alias
import sakayomi.gradle.extensions.android
import sakayomi.gradle.extensions.api
import sakayomi.gradle.extensions.debugApi
import sakayomi.gradle.extensions.implementation
import sakayomi.gradle.extensions.libs
import sakayomi.gradle.extensions.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("UNUSED")
class PluginComposeAndroid : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        plugins {
            alias(libs.plugins.kotlin.compose.compiler)
        }

        android {
            buildFeatures {
                compose = true
            }
        }

        dependencies {
            implementation(platform(libs.androidx.compose.bom))

            // Compose @Preview tooling
            api(libs.androidx.compose.uiToolingPreview)
            debugApi(libs.androidx.compose.uiTooling)
        }
    }
}
