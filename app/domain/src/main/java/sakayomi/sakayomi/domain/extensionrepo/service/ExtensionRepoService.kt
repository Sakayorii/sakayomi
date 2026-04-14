package sakayomi.domain.extensionrepo.service

import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.network.NetworkHelper
import dev.sakayori.sakayomi.network.awaitSuccess
import dev.sakayori.sakayomi.network.parseAs
import kotlinx.serialization.json.Json
import logcat.LogPriority
import sakayomi.domain.extensionrepo.model.ExtensionRepo
import tachiyomi.core.common.util.lang.withIOContext
import tachiyomi.core.common.util.system.logcat

class ExtensionRepoService(
    networkHelper: NetworkHelper,
    private val json: Json,
) {
    val client = networkHelper.client

    suspend fun fetchRepoDetails(
        repo: String,
    ): ExtensionRepo? {
        return withIOContext {
            try {
                with(json) {
                    client.newCall(GET("$repo/repo.json"))
                        .awaitSuccess()
                        .parseAs<ExtensionRepoMetaDto>()
                        .toExtensionRepo(baseUrl = repo)
                }
            } catch (e: Exception) {
                logcat(LogPriority.ERROR, e) { "Failed to fetch repo details" }
                null
            }
        }
    }
}
