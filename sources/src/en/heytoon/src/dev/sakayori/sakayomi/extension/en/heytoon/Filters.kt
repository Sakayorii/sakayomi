package dev.sakayori.sakayomi.extension.en.heytoon

import dev.sakayori.sakayomi.source.model.Filter
import dev.sakayori.sakayomi.source.model.FilterList

class SortFilter(state: Int = 0) :
    Filter.Select<String>(
        name = "Sort",
        values = sortBy.map { it.first }.toTypedArray(),
        state = state,
    ) {
    val sort get() = sortBy[state].second

    companion object {
        val popular = FilterList(SortFilter(1))
        val latest = FilterList(SortFilter(0))
    }
}

private val sortBy = arrayOf(
    "Most Recent" to "latest",
    "Most Viewed" to "views",
)

class GenreFilter : Filter.Select<String>("Genres", genres) {
    val selected get() = genres[state].takeIf { state != 0 }
}

private val genres = arrayOf(
    "All",
    "Detective",
    "Spin-Off",
    "Mommy",
    "Uncensored",
    "New",
    "In-Law",
    "Cheating",
    "MILF",
    "Harem",
    "College",
    "Business",
    "Supernatural",
    "Thriller",
    "Adventure",
    "Romance",
    "Drama",
)
