package dev.sakayori.sakayomi.extension.tr.lunascans

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.source.model.Page
import org.jsoup.nodes.Document
import java.text.SimpleDateFormat
import java.util.Locale

class LunaScans :
    Madara(
        "Luna Scans",
        "https://tuhafscans.com",
        "tr",
        dateFormat = SimpleDateFormat("d MMMM yyyy", Locale("tr")),
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Always
    override val useNewChapterEndpoint = false

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
