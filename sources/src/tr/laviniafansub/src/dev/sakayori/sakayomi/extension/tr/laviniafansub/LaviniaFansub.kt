package dev.sakayori.sakayomi.extension.tr.laviniafansub

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.source.model.Page
import org.jsoup.nodes.Document
import java.text.SimpleDateFormat
import java.util.Locale

class LaviniaFansub :
    Madara(
        "Lavinia Fansub",
        "https://laviniafansub.mom",
        "tr",
        dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT),
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Always

    override val useNewChapterEndpoint = true

    override val chapterUrlSelector = "a:not(:has(img))"

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
}
