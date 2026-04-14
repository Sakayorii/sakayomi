package dev.sakayori.sakayomi.extension.ja.comicborder

import dev.sakayori.sakayomi.multisrc.gigaviewer.GigaViewer
import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.source.model.FilterList
import dev.sakayori.sakayomi.source.model.MangasPage
import dev.sakayori.sakayomi.source.model.SManga
import okhttp3.Request
import okhttp3.Response
import org.jsoup.nodes.Element

class ComicBorder :
    GigaViewer(
        "Comic Border",
        "https://comicborder.com",
        "ja",
    ) {
    override val supportsLatest = false

    override fun popularMangaRequest(page: Int): Request = GET(baseUrl, headers)

    override val popularMangaSelector = "section.top-series"

    override fun popularMangaFromElement(element: Element): SManga = SManga.create().apply {
        setUrlWithoutDomain(element.selectFirst(".top-series-nav a")!!.absUrl("href"))
        title = element.selectFirst("h3")!!.text()
        thumbnail_url = element.selectFirst(".top-key-image")?.absUrl("data-src")
    }

    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request = throw UnsupportedOperationException()
    override fun searchMangaParse(response: Response): MangasPage = throw UnsupportedOperationException()
}
