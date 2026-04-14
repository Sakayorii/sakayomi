package dev.sakayori.sakayomi.extension.en.omegascans

import dev.sakayori.sakayomi.multisrc.heancms.HeanCms
import dev.sakayori.sakayomi.network.interceptor.rateLimitHost
import okhttp3.HttpUrl.Companion.toHttpUrl

class OmegaScans : HeanCms("Omega Scans", "https://omegascans.org", "en") {

    override val client = super.client.newBuilder()
        .rateLimitHost(apiUrl.toHttpUrl(), 1)
        .build()

    // Site changed from MangaThemesia to HeanCms.
    override val versionId = 2

    override val useNewChapterEndpoint = true
    override val useNewQueryEndpoint = true
    override val enableLogin = true
}
