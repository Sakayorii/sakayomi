package dev.sakayori.sakayomi.data.backup.create.creators

import dev.sakayori.sakayomi.data.backup.models.BackupExtensionRepos
import dev.sakayori.sakayomi.data.backup.models.backupExtensionReposMapper
import sakayomi.domain.extensionrepo.interactor.GetExtensionRepo
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class ExtensionRepoBackupCreator(
    private val getExtensionRepos: GetExtensionRepo = Injekt.get(),
) {

    suspend operator fun invoke(): List<BackupExtensionRepos> {
        return getExtensionRepos.getAll()
            .map(backupExtensionReposMapper)
    }
}
