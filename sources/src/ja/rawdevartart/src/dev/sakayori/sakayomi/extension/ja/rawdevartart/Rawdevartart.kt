package dev.sakayori.sakayomi.extension.ja.rawdevartart

import dev.sakayori.sakayomi.extension.ja.rawdevartart.dto.ChapterResponseDto
import dev.sakayori.sakayomi.extension.ja.rawdevartart.dto.MangaListResponseDto
import dev.sakayori.sakayomi.extension.ja.rawdevartart.dto.MangaResponseDto
import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.source.model.Filter
import dev.sakayori.sakayomi.source.model.FilterList
import dev.sakayori.sakayomi.source.model.MangasPage
import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.source.online.HttpSource
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Request
import okhttp3.Response
import uy.kohesive.injekt.injectLazy

class Rawdevartart : HttpSource() {

    override val name = "Rawdevart.art"

    override val lang = "ja"

    override val baseUrl = "https://rawdevart.art"

    override val supportsLatest = true

    override fun headersBuilder() = super.headersBuilder()
        .add("Referer", "$baseUrl/")

    private val json: Json by injectLazy()

    override fun popularMangaRequest(page: Int) = searchMangaRequest(
        page,
        "",
        FilterList(
            SortFilter(1),
            GenreFilter(genres),
        ),
    )

    override fun popularMangaParse(response: Response) = searchMangaParse(response)

    override fun latestUpdatesRequest(page: Int) = searchMangaRequest(
        page,
        "",
        FilterList(
            SortFilter(0),
            GenreFilter(genres),
        ),
    )

    override fun latestUpdatesParse(response: Response) = searchMangaParse(response)

    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request {
        val url = "$baseUrl/spa".toHttpUrl().newBuilder().apply {
            addQueryParameter("page", page.toString())

            if (query.isNotEmpty()) {
                addPathSegment("search")
                addQueryParameter("query", query)

                return@apply
            }

            (if (filters.isEmpty()) getFilterList() else filters).forEach { f ->
                when (f) {
                    is UriFilter -> f.addToUri(this)
                    is GenreFilter -> {
                        addPathSegment("genre")
                        addPathSegment(f.values[f.state].path)
                    }

                    else -> {}
                }
            }
        }.build()

        return GET(url, headers)
    }

    override fun searchMangaParse(response: Response): MangasPage {
        val data = response.parseAs<MangaListResponseDto>()

        return data.toMangasPage()
    }

    override fun mangaDetailsParse(response: Response): SManga {
        val data = response.parseAs<MangaResponseDto>()

        return data.toSManga()
    }

    override fun chapterListParse(response: Response): List<SChapter> {
        val data = response.parseAs<MangaResponseDto>()

        return data.toSChapterList()
    }

    override fun pageListParse(response: Response): List<Page> {
        val data = response.parseAs<ChapterResponseDto>()

        return data.toPageList()
    }

    override fun imageUrlParse(response: Response) = throw UnsupportedOperationException()

    override fun getFilterList() = FilterList(
        Filter.Header("Filters are ignored when using text search."),
        StatusFilter(),
        SortFilter(),
        GenreFilter(genres),
    )

    private inline fun <reified T> Response.parseAs(): T = json.decodeFromString(body.string())
}
