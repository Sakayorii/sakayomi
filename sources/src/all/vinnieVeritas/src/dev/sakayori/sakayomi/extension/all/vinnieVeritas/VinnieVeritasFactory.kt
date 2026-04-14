package dev.sakayori.sakayomi.extension.all.vinnieVeritas

import dev.sakayori.sakayomi.source.Source
import dev.sakayori.sakayomi.source.SourceFactory

class VinnieVeritasFactory : SourceFactory {

    override fun createSources(): List<Source> = listOf(
        VinnieVeritas("en"),
        VinnieVeritas("es"),
    )
}
