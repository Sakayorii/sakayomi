package dev.sakayori.sakayomi.extension.en.existentialcomics

import dev.sakayori.sakayomi.source.model.FilterList
import dev.sakayori.sakayomi.source.model.MangasPage
import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.source.online.ParsedHttpSource
import okhttp3.Request
import okhttp3.Response
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import rx.Observable

class ExistentialComics : ParsedHttpSource() {

    override val name = "Existential Comics"

    override val baseUrl = "https://existentialcomics.com"

    override val lang = "en"

    override val supportsLatest = false

    override fun fetchPopularManga(page: Int): Observable<MangasPage> {
        val manga = SManga.create().apply {
            title = "Existential Comics"
            artist = "Corey Mohler"
            author = "Corey Mohler"
            status = SManga.ONGOING
            url = "/archive/byDate"
            description = "A philosophy comic about the inevitable anguish of living a brief life in an absurd world. Also Jokes."
            thumbnail_url = "https://i.ibb.co/pykMVYM/existential-comics.png"
        }

        return Observable.just(MangasPage(arrayListOf(manga), false))
    }

    override fun fetchSearchManga(page: Int, query: String, filters: FilterList): Observable<MangasPage> = Observable.just(MangasPage(emptyList(), false))

    override fun fetchMangaDetails(manga: SManga) = Observable.just(manga)

    override fun chapterListParse(response: Response): List<SChapter> = super.chapterListParse(response).distinct().reversed()

    override fun chapterListSelector() = "div#date-comics ul li a:eq(0)"

    override fun chapterFromElement(element: Element): SChapter {
        val chapter = SChapter.create()
        chapter.setUrlWithoutDomain(element.attr("href"))
        chapter.name = element.text()
        chapter.chapter_number = chapter.url.substringAfterLast("/").toFloat()
        return chapter
    }

    override fun pageListParse(document: Document) = document.select(".comicImg").mapIndexed { i, element -> Page(i, "", "https:" + element.attr("src").substring(1)) }

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
