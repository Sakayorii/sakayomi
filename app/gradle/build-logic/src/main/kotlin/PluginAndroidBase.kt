import sakayomi.gradle.configurations.configureAndroid
import sakayomi.gradle.configurations.configureKotlin
import sakayomi.gradle.extensions.alias
import sakayomi.gradle.extensions.configureTest
import sakayomi.gradle.extensions.libs
import sakayomi.gradle.extensions.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("UNUSED")
class PluginAndroidBase : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        plugins {
            alias(libs.plugins.kotlin.android)
        }

        configureKotlin()
        configureTest()
        configureAndroid()
    }
}
