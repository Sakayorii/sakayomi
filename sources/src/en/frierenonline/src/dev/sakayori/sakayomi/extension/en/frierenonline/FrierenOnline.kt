package dev.sakayori.sakayomi.extension.en.frierenonline

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.source.model.FilterList
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.util.asJsoup
import okhttp3.Response
import org.jsoup.nodes.Document

class FrierenOnline :
    Madara(
        "Frieren Online",
        "https://www.frieren.online",
        "en",
    ) {
    override val supportsLatest = false
    override val useLoadMoreRequest = LoadMoreStrategy.Always

    override fun getFilterList() = FilterList()

    override fun mangaDetailsParse(document: Document): SManga = SManga.create().apply {
        title = document.selectFirst(".about h1")!!.text()
        thumbnail_url = document.selectFirst(".cover_managa img")?.attr("abs:src")
        description = document.selectFirst(".synopsis p")?.text()
        author = document.selectFirst("h5:contains(Author) + h4")?.text()
        artist = document.selectFirst("h5:contains(Artist) + h4")?.text()
        genre = document.select(".tags a[rel=tag]").joinToString { it.text() }
        status = when (document.selectFirst("h5:contains(Status) + h4")?.text()) {
            "OnGoing" -> SManga.ONGOING
            "Completed" -> SManga.COMPLETED
            else -> SManga.UNKNOWN
        }
    }

    override fun chapterListParse(response: Response): List<SChapter> {
        val document = response.asJsoup()

        return document.select("li.m-chapter a:has(.chapter-content)").map { element ->
            SChapter.create().apply {
                setUrlWithoutDomain(element.attr("abs:href"))
                name = element.selectFirst(".chapter-content > div")!!.text()
            }
        }
    }
}
