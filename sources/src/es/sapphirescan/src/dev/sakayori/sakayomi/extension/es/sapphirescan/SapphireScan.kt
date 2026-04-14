package dev.sakayori.sakayomi.extension.es.sapphirescan

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimitHost
import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.source.model.SChapter
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.text.SimpleDateFormat
import java.util.Locale

class SapphireScan :
    Madara(
        "SapphireScan",
        "https://sapphirescan.com",
        "es",
        SimpleDateFormat("MMMM dd, yyyy", Locale("es")),
    ) {
    override val client = super.client.newBuilder()
        .rateLimitHost(baseUrl.toHttpUrl(), 3)
        .build()

    override val useNewChapterEndpoint = true
    override val useLoadMoreRequest = LoadMoreStrategy.Always

    override fun chapterFromElement(element: Element): SChapter = super.chapterFromElement(element).apply {
        if (element.select("span.required-login").isNotEmpty()) {
            name = "🔒 $name"
        }
    }

    override fun pageListParse(document: Document): List<Page> {
        val pageList = super.pageListParse(document)

        if (
            pageList.isEmpty() &&
            document.select(".content-blocked, .login-required").isNotEmpty()
        ) {
            throw Exception("Inicie sesión en WebView para ver este capítulo")
        }
        return pageList
    }
}
