package dev.sakayori.sakayomi.extension.ja.klraw

import dev.sakayori.sakayomi.multisrc.mangareader.MangaReader
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import dev.sakayori.sakayomi.source.model.Filter
import dev.sakayori.sakayomi.source.model.FilterList
import okhttp3.HttpUrl

class KLRaw :
    MangaReader(
        "KL Raw",
        "https://www.klraw.info",
        "ja",
    ) {

    override val client = super.client.newBuilder()
        .rateLimit(2)
        .build()

    override fun headersBuilder() = super.headersBuilder()
        .add("Referer", "$baseUrl/")

    override fun addPage(page: Int, builder: HttpUrl.Builder) {
        builder.addQueryParameter("p", page.toString())
    }

    // =============================== Search ===============================

    override val searchPathSegment = ""
    override val searchKeyword = "q"

    // ============================== Chapters ==============================

    override val chapterIdSelect = "ja-chaps"

    // =============================== Pages ================================

    override fun getAjaxUrl(id: String): String = "$baseUrl/json/chapter?mode=vertical&id=$id"

    // =============================== Filters ==============================

    override fun getFilterList() = FilterList(
        Note,
        Filter.Separator(),
        TypeFilter(),
        StatusFilter(),
        LanguageFilter(),
        getSortFilter(),
    )

    override fun sortFilterValues(): Array<Pair<String, String>> = arrayOf(
        Pair("デフォルト", "default"),
        Pair("最新の更新", "latest-updated"),
        Pair("最も見られました", "most-viewed"),
        Pair("Title [A-Z]", "title-az"),
        Pair("Title [Z-A]", "title-za"),
    )
}
