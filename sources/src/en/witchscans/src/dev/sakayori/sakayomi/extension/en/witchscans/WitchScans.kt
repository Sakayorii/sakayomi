package dev.sakayori.sakayomi.extension.en.witchscans

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.source.model.FilterList

class WitchScans :
    MangaThemesia(
        "WitchScans",
        "https://witchscans.com",
        "en",
    ) {
    override fun chapterListSelector() = "div.eplister ul li:has(div.chbox):has(div.eph-num):has(a[href])"

    override fun getFilterList(): FilterList {
        val filters = super.getFilterList().filterNot { it is AuthorFilter || it is YearFilter }
        return FilterList(filters)
    }
}
