package dev.sakayori.sakayomi.extension.ja.mangabang

import dev.sakayori.sakayomi.multisrc.comiciviewer.ComiciViewerAlt
import dev.sakayori.sakayomi.network.GET
import okhttp3.Request

class MangaBang :
    ComiciViewerAlt(
        "MangaBang Comics",
        "https://comics.manga-bang.com",
        "ja",
        "https://comics.manga-bang.com/api",
    ) {
    override fun latestUpdatesRequest(page: Int): Request = GET("$baseUrl/category/manga/$page", headers)

    override fun getFilterOptions(): List<Pair<String, String>> = listOf(
        Pair("ランキング", "/ranking/manga"),
        Pair("更新順", "/series/list/up"),
        Pair("新作順", "/series/list/new"),
        Pair("完結", "/category/manga/complete"),
    )
}
