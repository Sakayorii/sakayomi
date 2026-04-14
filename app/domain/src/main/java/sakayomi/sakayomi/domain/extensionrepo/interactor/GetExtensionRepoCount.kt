package sakayomi.domain.extensionrepo.interactor

import sakayomi.domain.extensionrepo.repository.ExtensionRepoRepository

class GetExtensionRepoCount(
    private val repository: ExtensionRepoRepository,
) {
    fun subscribe() = repository.getCount()
}
