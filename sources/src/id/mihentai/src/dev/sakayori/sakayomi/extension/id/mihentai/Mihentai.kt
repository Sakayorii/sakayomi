package dev.sakayori.sakayomi.extension.id.mihentai

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.source.model.FilterList

class Mihentai : MangaThemesia("Mihentai", "https://mihentai.net", "id") {
    private class StatusFilter :
        SelectFilter(
            "Status",
            arrayOf(
                Pair("All", ""),
                Pair("Publishing", "publishing"),
                Pair("Finished", "finished"),
                Pair("Dropped", "drop"),
            ),
        )

    private class TypeFilter :
        SelectFilter(
            "Type",
            arrayOf(
                Pair("Default", ""),
                Pair("Manga", "Manga"),
                Pair("Manhwa", "Manhwa"),
                Pair("Manhua", "Manhua"),
                Pair("Webtoon", "webtoon"),
                Pair("One-Shot", "One-Shot"),
                Pair("Doujin", "doujin"),
            ),
        )

    override fun getFilterList(): FilterList = FilterList(
        listOf(
            StatusFilter(),
            TypeFilter(),
            OrderByFilter(intl["order_by_filter_title"], orderByFilterOptions),
            GenreListFilter(intl["genre_filter_title"], getGenreList()),
        ),
    )
}
