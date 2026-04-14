package dev.sakayori.sakayomi.extension.ja.comicdays

import dev.sakayori.sakayomi.multisrc.gigaviewer.GigaViewer
import dev.sakayori.sakayomi.source.model.SManga
import org.jsoup.nodes.Element

class ComicDays :
    GigaViewer(
        "Comic Days",
        "https://comic-days.com",
        "ja",
    ) {
    override val popularMangaSelector: String = "ul.daily-series li.daily-series-item:has(a.link)"

    override fun popularMangaFromElement(element: Element): SManga = SManga.create().apply {
        title = element.selectFirst("h4.daily-series-title")!!.text()
        thumbnail_url = element.selectFirst("div.daily-series-thumb img")?.absUrl("data-src")
        setUrlWithoutDomain(element.selectFirst("a.link")!!.absUrl("href"))
    }

    override val latestUpdatesSelector: String = "section#$dayOfWeek.daily $popularMangaSelector"

    override fun getCollections(): List<Collection> = listOf(
        Collection("連載作品一覧", ""),
    )
}
