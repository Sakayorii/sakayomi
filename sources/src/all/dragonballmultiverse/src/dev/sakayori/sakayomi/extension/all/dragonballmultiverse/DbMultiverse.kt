package dev.sakayori.sakayomi.extension.all.dragonballmultiverse

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

abstract class DbMultiverse(override val lang: String, private val internalLang: String) : ParsedHttpSource() {

    override val name =
        if (internalLang.endsWith("_PA")) {
            "Dragon Ball Multiverse Parody"
        } else {
            "Dragon Ball Multiverse"
        }
    override val baseUrl = "https://www.dragonball-multiverse.com"
    override val supportsLatest = false

    override fun chapterFromElement(element: Element): SChapter {
        val chapter = SChapter.create()
        val href = element.attr("href")
        chapter.setUrlWithoutDomain("/$internalLang/$href")
        chapter.name = "Page " + element.text()

        return chapter
    }

    override fun chapterListSelector(): String = ".cadrelect.chapter p a[href*=-]"

    override fun chapterListParse(response: Response): List<SChapter> = super.chapterListParse(response).reversed()

    override fun pageListParse(document: Document): List<Page> = document.select("#balloonsimg")
        .let { e ->
            listOf(
                if (e.hasAttr("src")) {
                    Page(1, "", e.attr("abs:src"))
                } else {
                    e.attr("style")
                        .substringAfter("(")
                        .substringBefore(")")
                        .let { Page(1, "", baseUrl + it) }
                },
            )
        }

    override fun fetchPopularManga(page: Int): Observable<MangasPage> {
        // site hosts three titles that can be read by the app
        return listOf("page", "strip", "namekseijin")
            .map { createManga(it) }
            .let { Observable.just(MangasPage(it, hasNextPage = false)) }
    }

    private fun createManga(type: String) = SManga.create().apply {
        title = when (type) {
            "comic" -> "DB Multiverse"
            "namekseijin" -> "Namekseijin Densetsu"
            "strip" -> "Minicomic"
            else -> name
        }
        status = SManga.ONGOING
        url = "/$internalLang/chapters.html?comic=$type"
        description = "Dragon Ball Multiverse (DBM) is a free online comic, made by a whole team of fans. It's our personal sequel to DBZ."
        thumbnail_url = "$baseUrl/imgs/read/$type.jpg"
    }

    override fun fetchMangaDetails(manga: SManga): Observable<SManga> = manga.apply {
        initialized = true
    }.let { Observable.just(it) }

    override fun mangaDetailsParse(document: Document): SManga = throw UnsupportedOperationException()

    override fun imageUrlParse(document: Document): String = throw UnsupportedOperationException()

    override fun popularMangaFromElement(element: Element): SManga = throw UnsupportedOperationException()

    override fun popularMangaRequest(page: Int): Request = throw UnsupportedOperationException()

    override fun popularMangaSelector(): String = throw UnsupportedOperationException()

    override fun popularMangaNextPageSelector(): String? = throw UnsupportedOperationException()

    override fun fetchSearchManga(page: Int, query: String, filters: FilterList): Observable<MangasPage> = Observable.just(MangasPage(emptyList(), false))

    override fun searchMangaFromElement(element: Element): SManga = throw UnsupportedOperationException()

    override fun searchMangaNextPageSelector(): String? = throw UnsupportedOperationException()

    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request = throw UnsupportedOperationException()

    override fun searchMangaSelector(): String = throw UnsupportedOperationException()

    override fun latestUpdatesFromElement(element: Element): SManga = throw UnsupportedOperationException()

    override fun latestUpdatesNextPageSelector(): String? = throw UnsupportedOperationException()

    override fun latestUpdatesRequest(page: Int): Request = throw UnsupportedOperationException()

    override fun latestUpdatesSelector(): String = throw UnsupportedOperationException()
}
