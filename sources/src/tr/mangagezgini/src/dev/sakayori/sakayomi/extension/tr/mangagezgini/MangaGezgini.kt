package dev.sakayori.sakayomi.extension.tr.mangagezgini

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.source.model.SManga
import okhttp3.Request
import org.jsoup.nodes.Document
import java.text.SimpleDateFormat
import java.util.Locale

class MangaGezgini :
    Madara(
        "MangaGezgini",
        "https://mangagezgini.online",
        "tr",
        SimpleDateFormat("dd/MM/yyyy", Locale.ROOT),
    ) {
    override val chapterUrlSelector = "> a"

    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true

    private var captchaUrl: String? = null

    override fun mangaDetailsRequest(manga: SManga): Request = captchaUrl?.let { GET(it, headers) }.also { captchaUrl = null }
        ?: super.mangaDetailsRequest(manga)

    override fun pageListParse(document: Document): List<Page> {
        if (document.selectFirst(".reading-content form, .reading-content input[value=Doğrula]") != null) {
            captchaUrl = document.selectFirst(".reading-content form")
                ?.attr("abs:action")
                ?: "$baseUrl/kontrol/"
            throw Exception("WebView'da captcha çözün.")
        }
        return super.pageListParse(document)
    }
}
