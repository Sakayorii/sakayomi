package dev.sakayori.sakayomi.extension.en.hentai4free

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.source.model.FilterList
import okhttp3.Request

class Hentai4Free : Madara("Hentai4Free", "https://hentai4free.net", "en") {
    override val useNewChapterEndpoint = true
    override val mangaSubString = "hentai"

    override fun popularMangaSelector() = searchMangaSelector()

    override fun popularMangaRequest(page: Int): Request = searchMangaRequest(
        page,
        "",
        FilterList(
            listOf(
                OrderByFilter(
                    "",
                    listOf(
                        Pair("", ""),
                        Pair("", "views"),
                    ),
                    1,
                ),
            ),
        ),
    )

    override fun latestUpdatesRequest(page: Int): Request = searchMangaRequest(
        page,
        "",
        FilterList(
            listOf(
                OrderByFilter(
                    "",
                    listOf(
                        Pair("", ""),
                        Pair("", "latest"),
                    ),
                    1,
                ),
            ),
        ),
    )
}
