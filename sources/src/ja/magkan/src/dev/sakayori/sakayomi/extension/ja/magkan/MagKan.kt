package dev.sakayori.sakayomi.extension.ja.magkan

import dev.sakayori.sakayomi.multisrc.comiciviewer.ComiciViewerAlt
import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.source.model.MangasPage
import okhttp3.Request
import okhttp3.Response

class MagKan :
    ComiciViewerAlt(
        "MagKan",
        "https://kansai.mag-garden.co.jp",
        "ja",
        "https://kansai.mag-garden.co.jp/api",
    ) {
    override val supportsLatest = false

    override fun popularMangaRequest(page: Int): Request = GET("$baseUrl/category/manga/$page", headers)

    override fun popularMangaParse(response: Response): MangasPage = latestUpdatesParse(response)

    override fun getFilterOptions(): List<Pair<String, String>> = listOf(
        Pair("更新順", "/series/list/up"),
        Pair("新作順", "/series/list/new"),
        Pair("読み切り", "/category/manga/oneShot"),
    )
}
