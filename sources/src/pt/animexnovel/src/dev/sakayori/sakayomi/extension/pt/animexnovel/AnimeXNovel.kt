package dev.sakayori.sakayomi.extension.pt.animexnovel

import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.network.POST
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import dev.sakayori.sakayomi.source.model.FilterList
import dev.sakayori.sakayomi.source.model.MangasPage
import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.source.online.HttpSource
import dev.sakayori.sakayomi.util.asJsoup
import Sakayorii.utils.parseAs
import okhttp3.FormBody
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.jsoup.nodes.Element
import rx.Observable
import java.util.concurrent.TimeUnit

class AnimeXNovel : HttpSource() {

    override val name = "AnimeXNovel"

    override val baseUrl: String = "https://www.animexnovel.com"

    override val lang: String = "pt-BR"

    override val supportsLatest: Boolean = true

    override val versionId: Int = 2

    override val client: OkHttpClient = network.cloudflareClient.newBuilder()
        .readTimeout(1, TimeUnit.MINUTES)
        .callTimeout(1, TimeUnit.MINUTES)
        .rateLimit(2)
        .build()

    // ========================== Popular ===================================

    override fun popularMangaRequest(page: Int): Request = GET("$baseUrl/mangas")

    override fun popularMangaParse(response: Response): MangasPage = response.mangaParse(".eael-post-grid-container article")

    // ========================== Latest ====================================

    override fun latestUpdatesRequest(page: Int): Request = GET(baseUrl)

    override fun latestUpdatesParse(response: Response): MangasPage = response.mangaParse("div:contains(Últimos Mangás) + div .manga-card")

    // ========================== Search ====================================

    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request {
        val form = FormBody.Builder()
            .add("action", "newscrunch_live_search")
            .add("keyword", query)
            .build()
        return POST("$baseUrl/wp-admin/admin-ajax.php", headers, form)
    }

    override fun searchMangaParse(response: Response): MangasPage {
        val mangas = response.asJsoup()
            .select(".search-wrapper")
            .map { element ->
                mangaFromElement(element).apply {
                    // Search returns manga and chapters, so this removes duplicate manga from the chapter list
                    url = url.substringBeforeLast("capitulo")
                    title = title.substringBeforeLast(DELIMITER_SEARCH_TITLE_REGEX)
                }
            }.distinctBy(SManga::url)
        return MangasPage(mangas, hasNextPage = false)
    }

    // ========================== Details ===================================

    override fun mangaDetailsParse(response: Response) = SManga.create().apply {
        val document = response.asJsoup()
        title = document.selectFirst("h2.spnc-entry-title")!!.text()
        author = document.selectFirst("li:contains(Autor:)")?.text()?.substringAfter(":")?.trim()
        artist = document.selectFirst("li:contains(Arte:)")?.text()?.substringAfter(":")?.trim()
        genre = document.selectFirst("li:contains(Arte:)")?.text()?.substringAfter(":")?.trim()
        description = document.selectFirst("meta[itemprop='description']")?.attr("content")
    }

    // ========================== Chapters ==================================

    override fun fetchChapterList(manga: SManga): Observable<List<SChapter>> = Observable.fromCallable {
        val document = client.newCall(mangaDetailsRequest(manga))
            .execute().asJsoup()

        val category = document.selectFirst("#container-capitulos")!!
            .attr("data-categoria")

        val url = "$baseUrl/wp-json/wp/v2/posts".toHttpUrl().newBuilder()
            .addQueryParameter("categories", category.toString())
            .addQueryParameter("orderby", "date")
            .addQueryParameter("order", "desc")
            .addQueryParameter("per_page", "100")

        val chapterList = mutableListOf<SChapter>()
        var page = 1
        while (true) {
            url.setQueryParameter("page", page.toString())
            val response = client.newCall(GET(url.build(), headers)).execute()
            if (!response.isSuccessful) {
                break
            }
            chapterList += chapterListParse(response)
            page++
        }
        chapterList
    }

    override fun chapterListParse(response: Response): List<SChapter> = response.parseAs<List<ChapterDto>>().map(ChapterDto::toSChapter)
        .filter { it.url.contains("capitulo") }

    // ========================== Pages =====================================

    private val pageContainerSelector = ".spice-block-img-gallery, .wp-block-gallery, .spnc-entry-content"

    override fun pageListParse(response: Response): List<Page> {
        val container = response.asJsoup().selectFirst(pageContainerSelector)!!
        return container.select("img").mapIndexed { index, element ->
            Page(index, imageUrl = element.absUrl("src"))
        }
    }

    override fun imageUrlParse(response: Response): String = ""

    // =========================== Utils ====================================

    private fun String.substringBeforeLast(regex: Regex): String {
        val substring = regex.find(this)?.groupValues?.last() ?: return this
        return substringBeforeLast(substring)
    }

    private fun Response.mangaParse(cssSelector: String): MangasPage {
        val mangas = asJsoup()
            .select(cssSelector)
            .map(::mangaFromElement)
        return MangasPage(mangas, hasNextPage = false)
    }

    private fun mangaFromElement(element: Element): SManga = SManga.create().apply {
        title = element.selectFirst("h2, h3, .search-content")!!.text()
        thumbnail_url = element.selectFirst("img")?.absUrl("src")
        setUrlWithoutDomain(element.selectFirst("a")!!.absUrl("href"))
    }

    companion object {
        private val DELIMITER_SEARCH_TITLE_REGEX = """[-–]""".toRegex()
    }
}
