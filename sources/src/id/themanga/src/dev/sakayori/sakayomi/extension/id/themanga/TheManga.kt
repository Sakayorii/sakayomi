package dev.sakayori.sakayomi.extension.id.themanga

import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import dev.sakayori.sakayomi.source.model.Filter
import dev.sakayori.sakayomi.source.model.FilterList
import dev.sakayori.sakayomi.source.model.MangasPage
import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.source.online.HttpSource
import dev.sakayori.sakayomi.util.asJsoup
import Sakayorii.utils.parseAs
import Sakayorii.utils.tryParse
import kotlinx.serialization.Serializable
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Request
import okhttp3.Response
import org.jsoup.nodes.Document
import java.text.SimpleDateFormat
import java.util.Locale

// Madara to HttpSource
class TheManga : HttpSource() {

    override val name = "TheManga"
    override val baseUrl = "https://themanga.my.id"
    override val lang = "id"
    override val supportsLatest = false

    override val client = network.client.newBuilder()
        .rateLimit(2)
        .build()

    // =============================== Popular ================================
    override fun popularMangaRequest(page: Int): Request = searchMangaRequest(page, "", FilterList())

    override fun popularMangaParse(response: Response): MangasPage = searchMangaParse(response)

    // =============================== Search =================================
    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request {
        val url = "$baseUrl/explore".toHttpUrl().newBuilder().apply {
            addQueryParameter("q", query)
            addQueryParameter("page", page.toString())

            filters.forEach { (it as? UrlFilter)?.addToUrl(this) }
        }.build()

        return GET(url, headers)
    }

    override fun searchMangaParse(response: Response): MangasPage {
        val document = response.asJsoup()

        val mangas = document.select("a.manga-card").map { element ->
            SManga.create().apply {
                setUrlWithoutDomain(element.attr("href"))
                title = element.selectFirst(".card-title")!!.text()
                thumbnail_url = element.selectFirst(".cover img")?.absUrl("src")
            }
        }

        val hasNextPage = document.selectFirst("a[rel=next]") != null
        return MangasPage(mangas, hasNextPage)
    }

    // =========================== Manga Details ============================
    override fun mangaDetailsParse(response: Response): SManga {
        val document = response.asJsoup()

        return SManga.create().apply {
            title = document.selectFirst(".hero-title")!!.text()
            author = document.meta("Author")
            artist = document.meta("Artist")
            description = document.selectFirst(".synopsis-text")?.text()
            thumbnail_url = document.selectFirst(".hero-cover img")?.absUrl("src")

            status = when (document.selectFirst(".hero-status-badge")?.text()?.lowercase()) {
                "ongoing" -> SManga.ONGOING
                "completed" -> SManga.COMPLETED
                else -> SManga.UNKNOWN
            }

            val genres = document.select(".meta-pill-row .meta-pill")
                .map { it.text() }
                .toMutableList()

            document.meta("Type")?.takeIf { it.isNotBlank() }?.let(genres::add)

            genre = genres.joinToString()
        }
    }

    // ============================== Chapters ==============================
    override fun chapterListRequest(manga: SManga): Request = GET("$baseUrl${manga.url}?all=1", headers)

    override fun chapterListParse(response: Response): List<SChapter> {
        val document = response.asJsoup()

        return document.select(".chapter-row").map { element ->
            SChapter.create().apply {
                setUrlWithoutDomain(element.attr("data-href"))
                name = element.selectFirst(".chapter-title")!!.text()
                date_upload = dateFormat.tryParse(element.selectFirst("[data-local-time]")?.attr("data-local-time"))
            }
        }
    }

    // =============================== Pages ================================
    override fun pageListRequest(chapter: SChapter): Request {
        if (!chapter.url.contains("/chapter/")) {
            OLD_URL_REGEX.find(chapter.url)?.let {
                val mangaSlug = it.groupValues[1]
                val number = it.groupValues[2].replace("-", ".")
                val dotIndex = number.indexOf(".")
                val formatted = if (dotIndex >= 0) {
                    number.padEnd(dotIndex + 3, '0')
                } else {
                    "$number.00"
                }
                return GET("$baseUrl/manga/$mangaSlug/chapter/$formatted", headers)
            }
        }
        return super.pageListRequest(chapter)
    }

    override fun pageListParse(response: Response): List<Page> {
        val document = response.asJsoup()

        val script = document.select("script").firstNotNullOfOrNull { PAGES_REGEX.find(it.data())?.groupValues?.get(1) }
            ?: throw Exception("Script yang berisi data halaman tidak ditemukan")

        return script.parseAs<List<PageDto>>()
            .map { Page(it.number - 1, "", it.url) }
    }

    override fun imageUrlParse(response: Response): String = throw UnsupportedOperationException()

    override fun latestUpdatesRequest(page: Int): Request = throw UnsupportedOperationException()

    override fun latestUpdatesParse(response: Response): MangasPage = throw UnsupportedOperationException()

    // ============================== Filters ===============================
    override fun getFilterList(): FilterList = FilterList(
        StatusFilter(),
        GenreFilter(),
        Filter.Separator(),
        OtherFilterGroup(),
    )

    // ============================== Utils ===============================
    private fun Document.meta(label: String): String? = selectFirst(".meta-item-label:matchesOwn(^$label$) + .meta-item-value")?.text()

    @Serializable
    data class PageDto(
        val number: Int,
        val url: String,
    )

    companion object {
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US)
        private val PAGES_REGEX = Regex("""pages\s*=\s*(\[[\s\S]*?])""")
        private val OLD_URL_REGEX = Regex("""/manga/([^/]+)/chapter-(\d+(?:-\d+)?)""")
    }
}
