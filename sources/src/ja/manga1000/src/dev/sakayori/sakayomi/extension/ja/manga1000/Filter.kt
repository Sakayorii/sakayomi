package dev.sakayori.sakayomi.extension.ja.manga1000

import dev.sakayori.sakayomi.source.model.Filter

abstract class UriPartFilter(
    displayName: String,
    private val vals: Array<Pair<String, String>>,
) : Filter.Select<String>(displayName, vals.map { it.first }.toTypedArray()) {
    fun toUriPart() = vals[state].second
}

class CategoryFilter :
    UriPartFilter(
        "Category",
        arrayOf(
            Pair("All", ""),
            Pair("Action", "6"),
            Pair("Adult", "7"),
            Pair("Adventure", "8"),
            Pair("Ecchi", "11"),
            Pair("Fantasy", "12"),
            Pair("Harem", "14"),
        ),
    )
