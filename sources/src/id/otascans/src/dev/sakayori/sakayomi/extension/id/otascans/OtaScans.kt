package dev.sakayori.sakayomi.extension.id.otascans

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import dev.sakayori.sakayomi.source.model.Page
import org.jsoup.nodes.Document

class OtaScans :
    MangaThemesia(
        "Ota Scans",
        "https://yurilabs.my.id",
        "id",
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(2)
        .build()

    override fun pageListParse(document: Document): List<Page> = super.pageListParse(document).takeIf { it.isNotEmpty() }
        ?: throw Exception("Maybe this content needs a password. Open in WebView")
}
