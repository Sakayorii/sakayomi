package dev.sakayori.sakayomi.extension.pt.apenasumafa

import dev.sakayori.sakayomi.multisrc.zeistmanga.ZeistManga
import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.util.asJsoup
import okhttp3.Response
import org.jsoup.nodes.Document

class ApenasUmaFa :
    ZeistManga(
        "Apenas Uma Fã",
        "https://apenasuma-fa.blogspot.com",
        "pt-BR",
    ) {
    override val supportsLatest = false

    override fun fetchPopularManga(page: Int) = fetchLatestUpdates(page)

    override fun mangaDetailsParse(response: Response) = SManga.create().apply {
        val document = response.asJsoup()
        title = document.selectFirst("h1")!!.text()
        thumbnail_url = document.selectFirst("thum")
            ?.attr("style")
            ?.substringAfter("url(\"")
            ?.substringBeforeLast("\"")
        description = document.selectFirst("#synopsis")?.text()
        genre = document.select("a[href*='search/label'].leading-none").joinToString { it.text() }
        document.selectFirst("div[class*=bg-green] span")?.ownText()?.let {
            status = when (it.lowercase()) {
                "em lançamento" -> SManga.ONGOING
                else -> SManga.UNKNOWN
            }
        }
    }

    override fun getChapterFeedUrl(doc: Document): String {
        val feed = doc.selectFirst(".chapter_get")!!.attr("data-labelchapter")
        return apiUrl(feed)
            .addQueryParameter("start-index", "1")
            .addQueryParameter("max-results", MAX_CHAPTER_RESULTS.toString())
            .build().toString()
    }

    override val pageListSelector = "#reader div.separator"
}
