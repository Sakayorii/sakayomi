package dev.sakayori.sakayomi.extension.zh.hanime1

import dev.sakayori.sakayomi.source.model.Filter

abstract class SelectFilter(
    name: String,
    private val options: List<Pair<String, String>>,
) : Filter.Select<String>(
    name,
    options.map { it.first }.toTypedArray(),
) {
    val selected get() = options[state].second.takeUnless { it.isEmpty() }
}

private val sortPairs = listOf(
    "最新" to "",
    "熱門：本日" to "popular-today",
    "熱門：本週" to "popular-week",
    "熱門：所有" to "popular",
)

class SortFilter : SelectFilter("Sort", sortPairs)
