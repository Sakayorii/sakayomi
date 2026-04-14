package dev.sakayori.sakayomi.extension.fr.scanr

import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.network.asObservableSuccess
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
import rx.Observable
import java.net.URI

class ScanR : HttpSource() {

    override val name = "ScanR"
    override val baseUrl = "https://teamscanr.fr"
    val cdnUrl = "https://cdn.teamscanr.fr"
    override val lang = "fr"
    override val supportsLatest = false
    private val seriesDataCache = mutableMapOf<String, Serie>()

    override fun getFilterList(): FilterList = FilterList(
        TypeFilter(),
        StatusFilter(),
        AdultFilter(),
    )

    // Popular
    override fun popularMangaRequest(page: Int): Request = GET("$cdnUrl/index.json", headers)

    override fun popularMangaParse(response: Response): MangasPage = searchMangaParse(response)

    // Search
    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request {
        val url = "$cdnUrl/index.json".toHttpUrl().newBuilder()
        if (query.isNotBlank()) {
            url.fragment(query)
        }
        filters.filterIsInstance<UriFilter>().forEach {
            it.addToUri(url)
        }

        return GET(url.build(), headers)
    }

    override fun searchMangaParse(response: Response): MangasPage {
        val series = response.parseAs<Map<String, String>>()
        val mangaList = mutableListOf<SManga>()

        val types = response.request.url.queryParameter("type") ?: "all"
        val status = response.request.url.queryParameter("status") ?: "all"
        val adult = response.request.url.queryParameter("adult") ?: "all"
        val searchQuery = response.request.url.fragment ?: ""

        if (searchQuery.startsWith("SLUG:")) {
            val filename = series.get(searchQuery.removePrefix("SLUG:"))
            if (filename != null) {
                val serie = fetchSeriesData(filename)
                mangaList.add(serie.toDetailedSManga())
            }
            return MangasPage(mangaList, false)
        }

        for ((slug, filename) in series) {
            val serie = fetchSeriesData(filename)

            if (searchQuery.isBlank() || serie.title.contains(searchQuery, ignoreCase = true)) {
                val details = serie.toDetailedSManga()
                if ((((serie.os && types.contains("os")) || (!serie.os && types.contains("series")) || types.contains("all"))) &&
                    ((((details.status == SManga.ONGOING) && status.contains("ongoing")) || (((details.status == SManga.COMPLETED) && status.contains("completed"))) || status.contains("all"))) &&
                    ((serie.konami && adult.contains("18")) || (!serie.konami && adult.contains("normal")) || adult.contains("all"))
                ) {
                    mangaList.add(details)
                }
            }
        }

        return MangasPage(mangaList, false)
    }

    // Details
    override fun fetchMangaDetails(manga: SManga): Observable<SManga> {
        val splitedPath = URI(manga.url).path.split("/")
        val slug = splitedPath[1]
        return client.newCall(GET("$cdnUrl/index.json", headers))
            .asObservableSuccess()
            .map { response ->
                mangaDetailsParse(response, slug)
            }
    }

    private fun mangaDetailsParse(response: Response, slug: String = ""): SManga {
        val map = response.parseAs<Map<String, String>>()
        val serie = fetchSeriesData(map.get(slug)!!, false)
        return serie.toDetailedSManga()
    }

    // Pages
    override fun pageListRequest(chapter: SChapter): Request {
        val splitedPath = URI(chapter.url).path.split("/")
        val slug = splitedPath[1]
        val chapterId = splitedPath[2]
        val serie = getSerieFromSlug(slug)
        val chapterDetails = serie.chapters[chapterId.replace("-", ".")]
        val cubariProxy = chapterDetails!!.groups.getValue(chapterDetails.groups.keys.first())
        return GET("https://cubari.moe$cubariProxy", headers)
    }

    override fun pageListParse(response: Response): List<Page> {
        val images = response.parseAs<List<String>>()
        return images.mapIndexed { index, pageData ->
            Page(index, imageUrl = pageData)
        }
    }

    // Chapters
    override fun fetchChapterList(manga: SManga): Observable<List<SChapter>> {
        val splitedPath = URI(manga.url).path.split("/")
        val slug = splitedPath[1]
        return client.newCall(GET("$cdnUrl/index.json", headers))
            .asObservableSuccess()
            .map { response ->
                chapterListParse(response, slug)
            }
    }

    private fun chapterListParse(response: Response, slug: String = ""): List<SChapter> {
        val filename = response.parseAs<Map<String, String>>().get(slug)!!
        val series = fetchSeriesData(filename)
        return buildChapterList(series)
    }

    private fun buildChapterList(serie: Serie): List<SChapter> {
        val chapters = serie.chapters
        val chapterList = mutableListOf<SChapter>()

        for ((chapterNumber, chapterData) in chapters) {
            val title = chapterData.title
            val volumeNumber = chapterData.volume

            val baseName = if (!serie.os) {
                buildString {
                    if (volumeNumber.isNotBlank()) append("Vol. $volumeNumber ")
                    append("Ch. $chapterNumber")
                    if (title.isNotBlank()) append(" – $title")
                }
            } else {
                if (title.isNotBlank()) "One Shot – $title" else "One Shot"
            }

            val chapter = SChapter.create().apply {
                name = baseName
                setUrlWithoutDomain("$baseUrl/${serie.slug}/${chapterNumber.replace(".","-")}")
                chapter_number = chapterNumber.toFloatOrNull() ?: -1f
                scanlator = chapterData.groups.keys.first()
                date_upload = chapterData.lastUpdated.toLong() * 1000L
            }
            chapterList.add(chapter)
        }

        return chapterList.sortedByDescending { it.chapter_number }
    }

    // Series utils
    private fun fetchSeriesData(filename: String, forceReload: Boolean = false): Serie {
        val cachedSerie = seriesDataCache[filename]
        if (!forceReload && cachedSerie != null) {
            return cachedSerie
        }

        val response = client.newCall(GET("$cdnUrl/$filename", headers)).execute()
        val seriesData = response.parseAs<Serie>()

        seriesDataCache[filename] = seriesData
        return seriesData
    }

    private fun getSerieFromSlug(slug: String, forceReload: Boolean = false): Serie {
        val serieList =
            client.newCall(GET("$cdnUrl/index.json", headers))
                .execute().parseAs<Map<String, String>>()

        return fetchSeriesData(serieList[slug] ?: "", forceReload)
    }

    // Unsupported stuff
    override fun imageUrlParse(response: Response): String = throw UnsupportedOperationException()

    override fun latestUpdatesParse(response: Response): MangasPage = throw UnsupportedOperationException()

    override fun latestUpdatesRequest(page: Int): Request = throw UnsupportedOperationException()

    override fun chapterListParse(response: Response): List<SChapter> = throw UnsupportedOperationException()

    override fun mangaDetailsParse(response: Response): SManga = throw UnsupportedOperationException()
}
