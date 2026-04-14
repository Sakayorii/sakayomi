package dev.sakayori.sakayomi.extension.all.mangapluscreators

import dev.sakayori.sakayomi.source.Source
import dev.sakayori.sakayomi.source.SourceFactory

class MangaPlusCreatorsFactory : SourceFactory {
    override fun createSources(): List<Source> = listOf(
        MangaPlusCreators("en"),
        MangaPlusCreators("es"),
    )
}
