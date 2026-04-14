package dev.sakayori.sakayomi.extension.en.manganow

import dev.sakayori.sakayomi.multisrc.mangareader.MangaReader
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import dev.sakayori.sakayomi.source.model.Filter
import dev.sakayori.sakayomi.source.model.FilterList

class MangaNow :
    MangaReader(
        "MangaNow",
        "https://manganow.to",
        "en",
    ) {

    override val client = super.client.newBuilder()
        .rateLimit(2)
        .build()

    override fun headersBuilder() = super.headersBuilder()
        .add("Referer", "$baseUrl/")

    // =============================== Pages ================================

    override fun pageListParseSelector() = ".container-reader-chapter > .iv-card:not([data-url$=manganow.jpg])"

    // =============================== Filters ==============================

    override fun getFilterList() = FilterList(
        Note,
        Filter.Separator(),
        TypeFilter(),
        StatusFilter(),
        ScoreFilter(),
        YearFilter(),
        getSortFilter(),
        GenreFilter(),
    )
}
