package dev.sakayori.sakayomi.extension.en.mlbblore

import dev.sakayori.sakayomi.network.POST
import dev.sakayori.sakayomi.source.model.FilterList
import dev.sakayori.sakayomi.source.model.MangasPage
import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.source.online.HttpSource
import Sakayorii.utils.parseAs
import okhttp3.FormBody
import okhttp3.Request
import okhttp3.Response

private const val SORT_NEWEST = 1
private const val SORT_POPULARITY = 3

class MLBBLore : HttpSource() {

    override val name = "MLBB Lore Comics"
    override val baseUrl = "https://play.mobilelegends.com"
    override val lang = "en"
    override val supportsLatest = true

    private val apiUrl = "https://api.mobilelegends.com"
    private val pageSize = 5

    private fun formRequest(url: String, params: Map<String, String>): Request {
        val formBody = FormBody.Builder()
        params.forEach { (key, value) -> formBody.add(key, value) }
        return POST(url, body = formBody.build())
    }

    private fun detailRequest(id: String): Request = formRequest(
        "$apiUrl/lore/album/detail",
        mapOf(
            "id" to id,
            "lang" to "en",
            "token" to "",
        ),
    )

    override fun popularMangaRequest(page: Int): Request = formRequest(
        "$apiUrl/lore/album/list",
        mapOf(
            "type" to TYPE_COMIC.toString(),
            "sort" to SORT_POPULARITY.toString(),
            "page" to page.toString(),
            "page_size" to pageSize.toString(),
            "lang" to "en",
            "token" to "",
        ),
    )

    override fun latestUpdatesRequest(page: Int): Request = formRequest(
        "$apiUrl/lore/album/list",
        mapOf(
            "type" to TYPE_COMIC.toString(),
            "sort" to SORT_NEWEST.toString(),
            "page" to page.toString(),
            "page_size" to pageSize.toString(),
            "lang" to "en",
            "token" to "",
        ),
    )

    private fun parseMangaListResponse(response: Response): MangasPage {
        val result = response.parseAs<ApiListResponse>()
        val mangas = result.data.filter { it.isComic() }.map { it.toSManga() }
        return MangasPage(mangas, result.data.size >= pageSize)
    }

    override fun popularMangaParse(response: Response): MangasPage = parseMangaListResponse(response)

    override fun latestUpdatesParse(response: Response): MangasPage = parseMangaListResponse(response)

    override fun mangaDetailsRequest(manga: SManga): Request = detailRequest(manga.url)

    override fun mangaDetailsParse(response: Response): SManga = response.parseAs<ApiDetailResponse>().data?.toSManga() ?: SManga.create()

    override fun chapterListRequest(manga: SManga): Request = detailRequest(manga.url)

    override fun chapterListParse(response: Response): List<SChapter> = response.parseAs<ApiDetailResponse>().data?.let { listOf(it.toSChapter()) } ?: return emptyList()

    override fun pageListRequest(chapter: SChapter): Request = detailRequest(chapter.url)

    override fun pageListParse(response: Response): List<Page> = response.parseAs<ApiDetailResponse>().data?.toPageList() ?: emptyList()

    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request = popularMangaRequest(page)

    override fun searchMangaParse(response: Response): MangasPage = parseMangaListResponse(response)

    override fun imageUrlParse(response: Response): String = throw UnsupportedOperationException()
}
