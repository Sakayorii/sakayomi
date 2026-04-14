package dev.sakayori.sakayomi.extension.uk.honeymanga

import dev.sakayori.sakayomi.extension.uk.honeymanga.dtos.CompleteHoneyMangaDto
import dev.sakayori.sakayomi.extension.uk.honeymanga.dtos.HoneyMangaChapterPagesDto
import dev.sakayori.sakayomi.extension.uk.honeymanga.dtos.HoneyMangaChapterResponseDto
import dev.sakayori.sakayomi.extension.uk.honeymanga.dtos.HoneyMangaDto
import dev.sakayori.sakayomi.extension.uk.honeymanga.dtos.HoneyMangaResponseDto
import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.network.POST
import dev.sakayori.sakayomi.network.interceptor.rateLimitHost
import dev.sakayori.sakayomi.source.model.FilterList
import dev.sakayori.sakayomi.source.model.MangasPage
import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.source.online.HttpSource
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import uy.kohesive.injekt.injectLazy
import java.text.SimpleDateFormat
import java.util.Locale

class HoneyManga : HttpSource() {

    override val name = "HoneyManga"
    override val baseUrl = "https://honey-manga.com.ua"
    override val lang = "uk"
    override val supportsLatest = true

    override fun headersBuilder() = super.headersBuilder()
        .add("Origin", baseUrl)
        .add("Referer", baseUrl)

    override val client = network.cloudflareClient.newBuilder()
        .rateLimitHost(API_URL.toHttpUrl(), 10)
        .build()

    // ============================== Popular ===============================
    override fun popularMangaRequest(page: Int) = makeHoneyMangaRequest(page, "likes")

    override fun popularMangaParse(response: Response) = parseAsMangaResponseDto(response)

    // =============================== Latest ===============================
    override fun latestUpdatesRequest(page: Int) = makeHoneyMangaRequest(page, "lastUpdated")

    override fun latestUpdatesParse(response: Response) = parseAsMangaResponseDto(response)

    // =============================== Search ===============================
    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request {
        if (query.length >= 3) {
            val url = "$SEARCH_API_URL/v2/manga/pattern".toHttpUrl().newBuilder()
                .addQueryParameter("query", query)
                .build()
            return GET(url, headers)
        } else {
            throw UnsupportedOperationException("Запит має містити щонайменше 3 символи / The query must contain at least 3 characters")
        }
    }

    override fun searchMangaParse(response: Response) = parseAsMangaResponseArray(response)

    // =========================== Manga Details ============================
    override fun mangaDetailsRequest(manga: SManga): Request {
        val mangaId = manga.url.substringAfterLast('/')
        val url = "$API_URL/manga/$mangaId"
        return GET(url, headers)
    }

    override fun mangaDetailsParse(response: Response) = SManga.create().apply {
        val mangaDto = response.asClass<CompleteHoneyMangaDto>()
        title = mangaDto.title
        thumbnail_url = "$IMAGE_STORAGE_URL/${mangaDto.posterId}"
        url = "$baseUrl/book/${mangaDto.id}"
        description = mangaDto.description
        genre = mangaDto.genresAndTags?.joinToString()
        artist = mangaDto.artists?.joinToString()
        author = mangaDto.authors?.joinToString()
        status = when (mangaDto.titleStatus.orEmpty()) {
            "Онгоінг" -> SManga.ONGOING
            "Завершено" -> SManga.COMPLETED
            "Покинуто" -> SManga.CANCELLED
            "Призупинено" -> SManga.ON_HIATUS
            else -> SManga.UNKNOWN
        }
    }

    // ============================== Chapters ==============================
    override fun chapterListRequest(manga: SManga): Request {
        val url = "$API_URL/v2/chapter/cursor-list"
        val body = buildJsonObject {
            put("mangaId", manga.url.substringAfterLast('/'))
            put("sortOrder", "DESC")
            put("page", "1")
            put("pageSize", "10000") // most likely there will not be any more pageSize
        }.toString().toRequestBody(JSON_MEDIA_TYPE)
        return POST(url, headers, body)
    }

    override fun chapterListParse(response: Response): List<SChapter> {
        val result = response.asClass<HoneyMangaChapterResponseDto>()
        return result.data.filter { !it.isMonetized }.map {
            val suffix = if (it.subChapterNum == 0) "" else ".${it.subChapterNum}"
            SChapter.create().apply {
                url = "$baseUrl/read/${it.id}/${it.mangaId}"
                name = "Vol. ${it.volume} Ch. ${it.chapterNum}$suffix"
                date_upload = it.lastUpdated.toDate()
            }
        }
    }

    // =============================== Pages ================================
    override fun pageListRequest(chapter: SChapter): Request {
        val chapterId = chapter.url.substringBeforeLast('/').substringAfterLast('/')
        val url = "$API_URL/chapter/frames/$chapterId"
        return GET(url, headers)
    }

    override fun pageListParse(response: Response): List<Page> {
        val data = response.asClass<HoneyMangaChapterPagesDto>()
        return data.resourceIds.map { (page, imageId) ->
            Page(page.toInt(), "", "$IMAGE_STORAGE_URL/$imageId")
        }
    }

    override fun imageUrlParse(response: Response): String = ""

    // ============================= Utilities ==============================
    private fun parseAsMangaResponseDto(response: Response): MangasPage {
        val mangaList = response.asClass<HoneyMangaResponseDto>().data
        return makeMangasPage(mangaList)
    }

    private fun parseAsMangaResponseArray(response: Response): MangasPage {
        val mangaList = response.asClass<List<HoneyMangaDto>>()
        return makeMangasPage(mangaList)
    }

    private fun makeHoneyMangaRequest(page: Int, sortBy: String): Request {
        val body = buildJsonObject {
            put("page", page)
            put("pageSize", DEFAULT_PAGE_SIZE)
            putJsonObject("sort") {
                put("sortBy", sortBy)
                put("sortOrder", "DESC")
            }
        }.toString().toRequestBody(JSON_MEDIA_TYPE)

        return POST("$API_URL/v2/manga/cursor-list", headers, body)
    }

    private fun makeMangasPage(mangaList: List<HoneyMangaDto>): MangasPage = MangasPage(
        mangaList.map(::makeSManga),
        mangaList.size == DEFAULT_PAGE_SIZE,
    )

    private fun makeSManga(mangaDto: HoneyMangaDto) = SManga.create().apply {
        title = mangaDto.title
        thumbnail_url = "$IMAGE_STORAGE_URL/${mangaDto.posterId}"
        url = "$baseUrl/book/${mangaDto.id}"
    }

    companion object {
        private const val API_URL = "https://data.api.honey-manga.com.ua"

        private const val SEARCH_API_URL = "https://search.api.honey-manga.com.ua"

        private const val IMAGE_STORAGE_URL = "https://hmvolumestorage.b-cdn.net/public-resources"

        private const val DEFAULT_PAGE_SIZE = 30

        private val JSON_MEDIA_TYPE = "application/json; charset=utf-8".toMediaType()

        private val DATE_FORMATTER by lazy {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ROOT)
        }

        private fun String.toDate(): Long = runCatching { DATE_FORMATTER.parse(this)?.time }
            .getOrNull() ?: 0L

        private val json: Json by injectLazy()

        private inline fun <reified T> Response.asClass(): T = use {
            json.decodeFromStream(it.body.byteStream())
        }
    }
}
