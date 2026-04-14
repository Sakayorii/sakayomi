package dev.sakayori.sakayomi.extension.en.swordscomic

import dev.sakayori.sakayomi.source.model.FilterList
import dev.sakayori.sakayomi.source.model.MangasPage
import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.source.online.HttpSource
import dev.sakayori.sakayomi.util.asJsoup
import Sakayorii.lib.textinterceptor.TextInterceptor
import Sakayorii.lib.textinterceptor.TextInterceptorHelper
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import rx.Observable
import java.text.SimpleDateFormat
import java.util.Locale

class SwordsComic : HttpSource() {

    override val name = "Swords Comic"

    override val baseUrl = "https://swordscomic.com"

    override val lang = "en"

    override val supportsLatest = false

    override val client: OkHttpClient = network.cloudflareClient.newBuilder().addInterceptor(TextInterceptor()).build()

    private fun createManga(): SManga = SManga.create().apply {
        title = "Swords Comic"
        url = "/archive/pages/"
        author = "Matthew Wills"
        artist = author
        description = "A webcomic about swords and the heroes who wield them"
        thumbnail_url = "https://swordscomic.com/media/ArgoksEdgeEmote.png"
    }

    // Popular

    override fun fetchPopularManga(page: Int): Observable<MangasPage> = Observable.just(MangasPage(listOf(createManga()), false))

    override fun popularMangaRequest(page: Int): Request = throw UnsupportedOperationException()

    override fun popularMangaParse(response: Response): MangasPage = throw UnsupportedOperationException()

    // Latest

    override fun latestUpdatesRequest(page: Int): Request = throw UnsupportedOperationException()

    override fun latestUpdatesParse(response: Response): MangasPage = throw UnsupportedOperationException()

    // Search

    override fun fetchSearchManga(page: Int, query: String, filters: FilterList): Observable<MangasPage> = Observable.just(MangasPage(emptyList(), false))

    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request = throw UnsupportedOperationException()

    override fun searchMangaParse(response: Response): MangasPage = throw UnsupportedOperationException()

    // Details

    override fun fetchMangaDetails(manga: SManga): Observable<SManga> = Observable.just(createManga().apply { initialized = true })

    override fun mangaDetailsParse(response: Response): SManga = throw UnsupportedOperationException()

    // Chapters

    override fun chapterListParse(response: Response): List<SChapter> = response.asJsoup().select("a.archive-tile")
        .map { element ->
            SChapter.create().apply {
                name = element.select("strong").text()
                setUrlWithoutDomain(element.attr("href"))
                date_upload = element.select("small").text()
                    .let { SimpleDateFormat("dd MMM yyyy", Locale.US).parse(it)?.time ?: 0L }
            }
        }
        .reversed()

    // Pages

    override fun pageListParse(response: Response): List<Page> {
        val imageElement = response.asJsoup().select("img#comic-image")
        if (!imageElement.hasAttr("title")) {
            return listOf(Page(0, "", imageElement.attr("abs:src")))
        }
        val titleText = TextInterceptorHelper.createUrl("", imageElement.attr("title"))

        return listOf(Page(0, "", imageElement.attr("abs:src")), Page(1, "", titleText))
    }

    override fun imageUrlParse(response: Response): String = throw UnsupportedOperationException()
}
