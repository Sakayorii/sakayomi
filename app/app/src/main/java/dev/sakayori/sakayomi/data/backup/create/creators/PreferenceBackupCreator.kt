package dev.sakayori.sakayomi.data.backup.create.creators

import dev.sakayori.sakayomi.data.backup.models.BackupPreference
import dev.sakayori.sakayomi.data.backup.models.BackupSourcePreferences
import dev.sakayori.sakayomi.data.backup.models.BooleanPreferenceValue
import dev.sakayori.sakayomi.data.backup.models.FloatPreferenceValue
import dev.sakayori.sakayomi.data.backup.models.IntPreferenceValue
import dev.sakayori.sakayomi.data.backup.models.LongPreferenceValue
import dev.sakayori.sakayomi.data.backup.models.StringPreferenceValue
import dev.sakayori.sakayomi.data.backup.models.StringSetPreferenceValue
import dev.sakayori.sakayomi.source.ConfigurableSource
import dev.sakayori.sakayomi.source.preferenceKey
import dev.sakayori.sakayomi.source.sourcePreferences
import tachiyomi.core.common.preference.Preference
import tachiyomi.core.common.preference.PreferenceStore
import tachiyomi.domain.source.service.SourceManager
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class PreferenceBackupCreator(
    private val sourceManager: SourceManager = Injekt.get(),
    private val preferenceStore: PreferenceStore = Injekt.get(),
) {

    fun createApp(includePrivatePreferences: Boolean): List<BackupPreference> {
        return preferenceStore.getAll().toBackupPreferences()
            .withPrivatePreferences(includePrivatePreferences)
    }

    fun createSource(includePrivatePreferences: Boolean): List<BackupSourcePreferences> {
        return sourceManager.getCatalogueSources()
            .filterIsInstance<ConfigurableSource>()
            .map {
                BackupSourcePreferences(
                    it.preferenceKey(),
                    it.sourcePreferences().all.toBackupPreferences()
                        .withPrivatePreferences(includePrivatePreferences),
                )
            }
            .filter { it.prefs.isNotEmpty() }
    }

    @Suppress("UNCHECKED_CAST")
    private fun Map<String, *>.toBackupPreferences(): List<BackupPreference> {
        return this
            .filterKeys { !Preference.isAppState(it) }
            .mapNotNull { (key, value) ->
                when (value) {
                    is Int -> BackupPreference(key, IntPreferenceValue(value))
                    is Long -> BackupPreference(key, LongPreferenceValue(value))
                    is Float -> BackupPreference(key, FloatPreferenceValue(value))
                    is String -> BackupPreference(key, StringPreferenceValue(value))
                    is Boolean -> BackupPreference(key, BooleanPreferenceValue(value))
                    is Set<*> -> (value as? Set<String>)?.let {
                        BackupPreference(key, StringSetPreferenceValue(it))
                    }
                    else -> null
                }
            }
    }

    private fun List<BackupPreference>.withPrivatePreferences(include: Boolean) =
        if (include) {
            this
        } else {
            this.filter { !Preference.isPrivate(it.key) }
        }
}
