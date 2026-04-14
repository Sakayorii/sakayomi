package dev.sakayori.sakayomi.extension.pt.lycantoons

import app.cash.quickjs.QuickJs
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
import Sakayorii.utils.jsonInstance
import Sakayorii.utils.parseAs
import kotlinx.serialization.encodeToString
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.jsoup.nodes.Element

class LycanToons : HttpSource() {

    override val name = "Lycan Toons"

    override val baseUrl = "https://lycantoons.com"

    override val lang = "pt-BR"

    override val supportsLatest = true

    override val client = network.cloudflareClient.newBuilder()
        .rateLimit(2)
        .build()

    private val pageHeaders by lazy {
        headers.newBuilder()
            .add("Referer", "$baseUrl/")
            .build()
    }

    // =====================Popular=====================

    override fun popularMangaRequest(page: Int): Request = metricsRequest("popular", page)

    override fun popularMangaParse(response: Response): MangasPage = response.parseAs<PopularResponse>().data.toMangasPage()

    // =====================Latest=====================

    override fun latestUpdatesRequest(page: Int): Request = metricsRequest("recently-updated", page)

    override fun latestUpdatesParse(response: Response): MangasPage = response.parseAs<PopularResponse>().data.toMangasPage()

    // =====================Search=====================

    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request {
        val payload = SearchRequestBody(
            limit = PAGE_LIMIT,
            page = page,
            search = query,
            seriesType = filters.valueOrEmpty<SeriesTypeFilter>(),
            status = filters.valueOrEmpty<StatusFilter>(),
            tags = filters.selectedTags(),
        )

        val body = json.encodeToString(payload).toRequestBody(JSON_MEDIA_TYPE)
        return POST("$baseUrl/api/series", headers, body)
    }

    override fun searchMangaParse(response: Response): MangasPage = response.parseAs<SearchResponse>().series.toMangasPage()

    override fun getFilterList(): FilterList = LycanToonsFilters.get()

    // =====================Details=====================

    override fun getMangaUrl(manga: SManga): String = "$baseUrl${manga.url}"

    override fun mangaDetailsRequest(manga: SManga): Request = seriesRequest(manga.slug())

    override fun mangaDetailsParse(response: Response): SManga {
        val result = response.parseAs<SeriesDto>()
        return result.toSManga()
    }

    // =====================Chapters=====================

    override fun chapterListRequest(manga: SManga): Request = seriesRequest(manga.slug())

    override fun chapterListParse(response: Response): List<SChapter> = response.parseAs<SeriesDto>().let { series ->
        series.capitulos!!
            .map { it.toSChapter(series.slug) }
            .sortedByDescending { it.chapter_number }
    }

    // =====================Pages========================

    override fun pageListRequest(chapter: SChapter): Request = GET(
        "$baseUrl${chapter.url}",
        pageHeaders,
    )

    override fun pageListParse(response: Response): List<Page> {
        val document = response.asJsoup()
        val script = document.select("script:containsData(self.__next_f)")
            .joinToString("\n", transform = Element::data)

        val content = QuickJs.create().use {
            it.evaluate(
                """
                globalThis.self = globalThis;
                $script
                self.__next_f.map(it => it[it.length - 1]).join('')
                """.trimIndent(),
            ) as String
        }

        val images = PAGES_REGEX.find(content)!!.groupValues.last().parseAs<List<String>>()

        return images.mapIndexed { index, imageUrl ->
            Page(index, imageUrl = imageUrl)
        }
    }

    override fun imageUrlParse(response: Response): String = throw UnsupportedOperationException()

    // =====================Utils=====================

    private fun metricsRequest(path: String, page: Int): Request = GET("$baseUrl/api/metrics/$path?limit=$PAGE_LIMIT&page=$page", headers)

    private fun List<SeriesDto>.toMangasPage(): MangasPage = MangasPage(map { it.toSManga() }, false)

    private fun seriesRequest(slug: String): Request = GET("$baseUrl/api/series/$slug", headers)

    private fun SManga.slug(): String = url.substringAfterLast("/")

    private val json by lazy { jsonInstance }

    companion object {
        private const val PAGE_LIMIT = 13
        private val JSON_MEDIA_TYPE = "application/json".toMediaType()
        private val PAGES_REGEX = """"imageUrls":([^]]+])""".toRegex()
    }
}
