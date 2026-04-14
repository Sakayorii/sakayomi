package dev.sakayori.sakayomi.extension.tr.strayfansub

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimitHost
import dev.sakayori.sakayomi.source.model.Page
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.jsoup.nodes.Document
import java.text.SimpleDateFormat
import java.util.Locale

class StrayFansub :
    Madara(
        "Stray Fansub",
        "https://strayfansub.net",
        "tr",
        dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale("tr")),
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true

    override fun pageListParse(document: Document): List<Page> {
        val pageList = super.pageListParse(document)

        if (
            pageList.isEmpty() &&
            document.select(".content-blocked, .login-required").isNotEmpty()
        ) {
            throw Exception("Okumak için WebView üzerinden giriş yapın")
        }
        return pageList
    }

    override val client = super.client.newBuilder()
        .rateLimitHost(baseUrl.toHttpUrl(), 3)
        .build()
}
