package dev.sakayori.sakayomi.extension.en.readvagabondmanga

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

class ReadVagabondManga : HttpSource() {
    override val name = "Read Vagabond Manga"
    override val baseUrl = "https://readbagabondo.com"
    override val lang = "en"
    override val supportsLatest = false

    override fun chapterListParse(response: Response): List<SChapter> {
        val chapters = response.parseAs<List<ChapterDto>>()
        return chapters.map { chapter ->
            chapter.toSChapter()
        }
    }

    override fun chapterListRequest(manga: SManga): Request {
        val mangaId = "$baseUrl${manga.url}".toHttpUrl().fragment
        return GET("$baseUrl/api/sakayomi/mangas/$mangaId/chapters", headers)
    }

    override fun imageUrlParse(response: Response): String = throw UnsupportedOperationException()

    override fun latestUpdatesParse(response: Response): MangasPage = throw UnsupportedOperationException()

    override fun latestUpdatesRequest(page: Int): Request = throw UnsupportedOperationException()

    override fun mangaDetailsParse(response: Response): SManga = response.parseAs<MangaDto>().toSManga()

    override fun mangaDetailsRequest(manga: SManga): Request {
        val mangaId = "$baseUrl${manga.url}".toHttpUrl().fragment
        return GET("$baseUrl/api/sakayomi/mangas/$mangaId", headers)
    }

    override fun pageListParse(response: Response): List<Page> {
        val chapter = response.parseAs<ChapterDto>()
        return (1..chapter.pageCount).map { page ->
            Page(
                index = page - 1,
                imageUrl = "https://bucket.readbagabondo.com/volume-%02d/chapter-%03d/page-%03d.png".format(
                    chapter.volume,
                    chapter.number,
                    page,
                ),
            )
        }
    }

    override fun pageListRequest(chapter: SChapter): Request {
        val mangaId = "$baseUrl${chapter.url}".toHttpUrl().fragment
        return GET(
            "$baseUrl/api/sakayomi/mangas/$mangaId/chapters/${chapter.chapter_number.toInt()}",
            headers,
        )
    }

    override fun popularMangaParse(response: Response): MangasPage {
        val mangas = response.parseAs<List<MangaDto>>()
        return MangasPage(mangas.map { manga -> manga.toSManga() }, false)
    }

    override fun popularMangaRequest(page: Int): Request = GET("$baseUrl/api/sakayomi/mangas", headers)

    override fun searchMangaParse(response: Response): MangasPage {
        val mangas = response.parseAs<List<MangaDto>>()
        return MangasPage(mangas.map { manga -> manga.toSManga() }, false)
    }

    override fun searchMangaRequest(
        page: Int,
        query: String,
        filters: FilterList,
    ): Request {
        val url = "$baseUrl/api/sakayomi/mangas".toHttpUrl().newBuilder()
            .addQueryParameter("q", query)
            .addQueryParameter("page", page.toString())
            .build()
        return GET(url, headers)
    }

    override fun getMangaUrl(manga: SManga): String = baseUrl

    override fun getChapterUrl(chapter: SChapter) = "$baseUrl/${chapter.url}"
}
