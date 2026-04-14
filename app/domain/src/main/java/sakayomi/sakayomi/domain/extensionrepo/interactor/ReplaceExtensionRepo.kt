package sakayomi.domain.extensionrepo.interactor

import sakayomi.domain.extensionrepo.model.ExtensionRepo
import sakayomi.domain.extensionrepo.repository.ExtensionRepoRepository

class ReplaceExtensionRepo(
    private val repository: ExtensionRepoRepository,
) {
    suspend fun await(repo: ExtensionRepo) {
        repository.replaceRepo(repo)
    }
}
