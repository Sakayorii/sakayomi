package dev.sakayori.sakayomi.extension.ar.mangatales

import dev.sakayori.sakayomi.multisrc.gmanga.Gmanga
import dev.sakayori.sakayomi.multisrc.gmanga.TagFilterData
import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.source.model.Filter
import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.util.asJsoup
import okhttp3.Request
import okhttp3.Response

class MangaTales :
    Gmanga(
        "Manga Tales",
        "https://www.mangatales.com",
        "ar",
        "https://media.mangatales.com",
    ) {
    override fun createThumbnail(mangaId: String, cover: String): String = "$cdnUrl/uploads/manga/cover/$mangaId/large_$cover"

    override fun getTypesFilter() = listOf(
        TagFilterData("1", "عربية", Filter.TriState.STATE_INCLUDE),
        TagFilterData("2", "إنجليزي", Filter.TriState.STATE_INCLUDE),
    )

    override fun chaptersRequest(manga: SManga): Request {
        val mangaId = manga.url.substringAfterLast("/")
        return GET("$baseUrl/api/mangas/$mangaId", headers)
    }

    override fun chaptersParse(response: Response): List<SChapter> {
        val releases = response.parseAs<ChapterListDto>().mangaReleases

        return releases.map { it.toSChapter() }
    }

    override fun pageListParse(response: Response): List<Page> {
        val data = response.asJsoup()
            .select(".js-react-on-rails-component").html()
            .parseAs<ReaderDto>()

        return data.readerDataAction.readerData.release.pages
            .mapIndexed { idx, img ->
                Page(idx, imageUrl = "$cdnUrl/uploads/releases/$img?ak=${data.globals.mediaKey}")
            }
    }
}
