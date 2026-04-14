package dev.sakayori.sakayomi.di

import android.app.Application
import dev.sakayori.domain.base.BasePreferences
import dev.sakayori.domain.source.service.SourcePreferences
import dev.sakayori.domain.track.service.TrackPreferences
import dev.sakayori.domain.ui.UiPreferences
import dev.sakayori.sakayomi.core.security.PrivacyPreferences
import dev.sakayori.sakayomi.core.security.SecurityPreferences
import dev.sakayori.sakayomi.network.NetworkPreferences
import dev.sakayori.sakayomi.ui.reader.setting.ReaderPreferences
import dev.sakayori.sakayomi.util.system.isDebugBuildType
import tachiyomi.core.common.preference.AndroidPreferenceStore
import tachiyomi.core.common.preference.PreferenceStore
import tachiyomi.core.common.storage.AndroidStorageFolderProvider
import tachiyomi.domain.backup.service.BackupPreferences
import tachiyomi.domain.download.service.DownloadPreferences
import tachiyomi.domain.library.service.LibraryPreferences
import tachiyomi.domain.storage.service.StoragePreferences
import tachiyomi.domain.updates.service.UpdatesPreferences
import uy.kohesive.injekt.api.InjektModule
import uy.kohesive.injekt.api.InjektRegistrar
import uy.kohesive.injekt.api.addSingletonFactory
import uy.kohesive.injekt.api.get

class PreferenceModule(val app: Application) : InjektModule {

    override fun InjektRegistrar.registerInjectables() {
        addSingletonFactory<PreferenceStore> {
            AndroidPreferenceStore(app)
        }
        addSingletonFactory {
            NetworkPreferences(
                preferenceStore = get(),
                verboseLoggingDefault = isDebugBuildType,
            )
        }
        addSingletonFactory {
            SourcePreferences(get())
        }
        addSingletonFactory {
            SecurityPreferences(get())
        }
        addSingletonFactory {
            PrivacyPreferences(get())
        }
        addSingletonFactory {
            LibraryPreferences(get())
        }
        addSingletonFactory {
            UpdatesPreferences(get())
        }
        addSingletonFactory {
            ReaderPreferences(get())
        }
        addSingletonFactory {
            TrackPreferences(get())
        }
        addSingletonFactory {
            DownloadPreferences(get())
        }
        addSingletonFactory {
            BackupPreferences(get())
        }
        addSingletonFactory {
            StoragePreferences(
                folderProvider = get<AndroidStorageFolderProvider>(),
                preferenceStore = get(),
            )
        }
        addSingletonFactory {
            UiPreferences(get())
        }
        addSingletonFactory {
            BasePreferences(app, get())
        }
    }
}
