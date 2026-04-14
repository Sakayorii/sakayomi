package dev.sakayori.sakayomi.extension.en.stonescape

import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.source.model.FilterList
import dev.sakayori.sakayomi.source.model.MangasPage
import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.source.online.HttpSource
import Sakayorii.utils.parseAs
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Request
import okhttp3.Response

class StoneScape : HttpSource() {

    override val name = "StoneScape"
    override val baseUrl = "https://stonescape.xyz"
    override val lang = "en"
    override val supportsLatest = true
    override val versionId = 2

    private val apiUrl = "$baseUrl/api"

    override fun headersBuilder() = super.headersBuilder()
        .add("Referer", "$baseUrl/")
        .add("Origin", baseUrl)

    // Popular
    override fun popularMangaRequest(page: Int): Request {
        val url = "$apiUrl/series".toHttpUrl().newBuilder()
            .addQueryParameter("page", page.toString())
            .addQueryParameter("limit", "24")
            .addQueryParameter("sort", "views")
            .addQueryParameter("contentType", "manhwa")
            .build()
        return GET(url, headers)
    }

    override fun popularMangaParse(response: Response): MangasPage {
        val result = response.parseAs<SeriesResponse>()
        val mangas = result.data.map { it.toSManga(baseUrl) }
        val hasNextPage = (result.pagination?.current ?: 1) < (result.pagination?.total ?: 1)
        return MangasPage(mangas, hasNextPage)
    }

    // Latest
    override fun latestUpdatesRequest(page: Int): Request {
        val url = "$apiUrl/series".toHttpUrl().newBuilder()
            .addQueryParameter("page", page.toString())
            .addQueryParameter("limit", "24")
            .addQueryParameter("sort", "latest")
            .addQueryParameter("contentType", "manhwa")
            .build()
        return GET(url, headers)
    }

    override fun latestUpdatesParse(response: Response): MangasPage = popularMangaParse(response)

    // Search
    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request {
        val url = "$apiUrl/series".toHttpUrl().newBuilder()
            .addQueryParameter("page", page.toString())
            .addQueryParameter("limit", "24")
            .addQueryParameter("contentType", "manhwa")

        if (query.isNotBlank()) {
            url.addQueryParameter("search", query)
        }

        filters.forEach { filter ->
            when (filter) {
                is StatusFilter -> {
                    if (filter.state != 0) {
                        url.addQueryParameter("status", filter.toUriPart())
                    }
                }
                is GenreFilter -> {
                    val genres = filter.state
                        .filter { it.state }
                        .joinToString(",") { it.slug }

                    if (genres.isNotEmpty()) {
                        url.addQueryParameter("genres", genres)
                    }
                }
                else -> {}
            }
        }

        return GET(url.build(), headers)
    }

    override fun searchMangaParse(response: Response): MangasPage = popularMangaParse(response)

    // Details
    override fun mangaDetailsRequest(manga: SManga): Request {
        val slug = manga.url.substringAfterLast("/")
        return GET("$apiUrl/series/by-slug/$slug", headers)
    }

    override fun mangaDetailsParse(response: Response): SManga = response.parseAs<SeriesDto>().toSMangaDetails(baseUrl)

    override fun getMangaUrl(manga: SManga): String = "$baseUrl${manga.url}"

    // Chapters
    override fun chapterListRequest(manga: SManga): Request {
        val slug = manga.url.substringAfterLast("/")
        return GET("$apiUrl/series/by-slug/$slug/chapters", headers)
    }

    override fun chapterListParse(response: Response): List<SChapter> {
        val result = response.parseAs<ChapterListResponse>()
        val seriesSlug = response.request.url.pathSegments.let { it[it.size - 2] }
        return result.chapters.map { it.toSChapter(seriesSlug) }.reversed()
    }

    override fun getChapterUrl(chapter: SChapter): String = "$baseUrl${chapter.url.substringBefore("#")}"

    // Pages
    override fun pageListRequest(chapter: SChapter): Request {
        val chapterId = chapter.url.substringAfter("#")
        return GET("$apiUrl/chapters/$chapterId/pages", headers)
    }

    override fun pageListParse(response: Response): List<Page> {
        val result = response.parseAs<ChapterDetailsDto>()
        return result.allPages.mapIndexed { index, page ->
            Page(index, imageUrl = baseUrl + page.url)
        }
    }

    override fun imageUrlParse(response: Response): String = throw UnsupportedOperationException()

    // Filters
    override fun getFilterList() = FilterList(
        StatusFilter(),
        GenreFilter(getGenreList()),
    )
}
