package dev.sakayori.sakayomi.extension.en.oglaf

import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.source.model.FilterList
import dev.sakayori.sakayomi.source.model.MangasPage
import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.source.online.ParsedHttpSource
import dev.sakayori.sakayomi.util.asJsoup
import okhttp3.Request
import okhttp3.Response
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import rx.Observable

class Oglaf : ParsedHttpSource() {

    override val name = "Oglaf"

    override val baseUrl = "https://www.oglaf.com"

    override val lang = "en"

    override val supportsLatest = false

    override fun fetchPopularManga(page: Int): Observable<MangasPage> {
        val manga = SManga.create().apply {
            title = "Oglaf"
            artist = "Trudy Cooper & Doug Bayne"
            author = "Trudy Cooper & Doug Bayne"
            status = SManga.ONGOING
            url = "/archive/"
            description = "Filth and other Fantastical Things in handy webcomic form."
            thumbnail_url = "https://i.ibb.co/tzY0VQ9/oglaf.png"
        }

        return Observable.just(MangasPage(arrayListOf(manga), false))
    }

    override fun fetchSearchManga(page: Int, query: String, filters: FilterList): Observable<MangasPage> = Observable.just(MangasPage(emptyList(), false))

    override fun fetchMangaDetails(manga: SManga): Observable<SManga> = Observable.just(manga)

    override fun chapterListParse(response: Response): List<SChapter> {
        val chapterList = super.chapterListParse(response).distinct()
        return chapterList.mapIndexed { i, ch ->
            ch.apply { chapter_number = chapterList.size.toFloat() - i }
        }
    }

    override fun chapterListSelector() = "a:has(img[width=400])"

    override fun chapterFromElement(element: Element): SChapter {
        val nameRegex =
            """/(.*)/""".toRegex()
        val chapter = SChapter.create()
        chapter.url = element.attr("href")
        chapter.name = nameRegex.find(element.attr("href"))!!.groupValues[1]
        return chapter
    }

    override fun pageListParse(document: Document): List<Page> {
        val urlRegex =
            """/.*/\d*/""".toRegex()
        val pages = mutableListOf<Page>()

        fun addPage(document: Document) {
            pages.add(Page(pages.size, "", document.select("img#strip").attr("abs:src")))
            val next = document.select("a[rel=next]").attr("href")
            if (urlRegex.matches(next)) addPage(client.newCall(GET(baseUrl + next, headers)).execute().asJsoup())
        }

        addPage(document)

        return pages
    }

    override fun imageUrlParse(document: Document) = throw UnsupportedOperationException()

    override fun popularMangaSelector(): String = throw UnsupportedOperationException()

    override fun searchMangaFromElement(element: Element): SManga = throw UnsupportedOperationException()

    override fun searchMangaNextPageSelector(): String? = throw UnsupportedOperationException()

    override fun searchMangaSelector(): String = throw UnsupportedOperationException()

    override fun popularMangaRequest(page: Int): Request = throw UnsupportedOperationException()

    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request = throw UnsupportedOperationException()

    override fun popularMangaNextPageSelector(): String? = throw UnsupportedOperationException()

    override fun popularMangaFromElement(element: Element): SManga = throw UnsupportedOperationException()

    override fun mangaDetailsParse(document: Document): SManga = throw UnsupportedOperationException()

    override fun latestUpdatesNextPageSelector(): String? = throw UnsupportedOperationException()

    override fun latestUpdatesFromElement(element: Element): SManga = throw UnsupportedOperationException()

    override fun latestUpdatesRequest(page: Int): Request = throw UnsupportedOperationException()

    override fun latestUpdatesSelector(): String = throw UnsupportedOperationException()
}
