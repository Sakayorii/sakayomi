import sakayomi.gradle.extensions.alias
import sakayomi.gradle.extensions.libs
import sakayomi.gradle.extensions.sakayomix
import sakayomi.gradle.extensions.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("UNUSED")
class PluginAndroidApplication : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        plugins {
            alias(libs.plugins.android.application)
            alias(sakayomix.plugins.android.base)
        }
    }
}
