package dev.sakayori.sakayomi.extension.all.comicskingdom

import dev.sakayori.sakayomi.source.Source
import dev.sakayori.sakayomi.source.SourceFactory

class ComicsKingdomFactory : SourceFactory {
    override fun createSources(): List<Source> = listOf(ComicsKingdom("en"), ComicsKingdom("es"))
}
