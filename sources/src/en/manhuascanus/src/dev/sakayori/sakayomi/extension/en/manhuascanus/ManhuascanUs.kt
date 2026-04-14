package dev.sakayori.sakayomi.extension.en.manhuascanus

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.source.model.Filter
import dev.sakayori.sakayomi.source.model.FilterList
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Request
import java.text.SimpleDateFormat
import java.util.Locale

class ManhuascanUs :
    MangaThemesia(
        "Manhuascan.us",
        "https://manhuascan.us",
        "en",
        mangaUrlDirectory = "/manga-list",
        dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ROOT),
    ) {
    override val seriesAuthorSelector = ".tsinfo .imptdt:contains(Author) a"

    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request {
        val url = baseUrl.toHttpUrl().newBuilder()
            .addPathSegment(mangaUrlDirectory.substring(1))
            .addQueryParameter("search", query)
            .addQueryParameter("page", page.toString())

        filters.forEach { filter ->
            when (filter) {
                is OrderByFilter -> {
                    url.addQueryParameter("order", filter.selectedValue())
                }

                else -> { /* Do Nothing */ }
            }
        }

        url.addPathSegment("")
        return GET(url.build(), headers)
    }

    override fun getFilterList(): FilterList {
        val orderByFilter = super.getFilterList().find { it is OrderByFilter } as? OrderByFilter

        return FilterList(
            Filter.Header("NOTE: Ignored if using text search!"),
            Filter.Separator(),
            orderByFilter!!,
        )
    }
}
