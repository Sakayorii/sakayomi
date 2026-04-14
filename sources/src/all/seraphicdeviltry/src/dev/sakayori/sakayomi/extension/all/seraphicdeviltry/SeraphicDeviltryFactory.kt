package dev.sakayori.sakayomi.extension.all.seraphicdeviltry

import dev.sakayori.sakayomi.source.Source
import dev.sakayori.sakayomi.source.SourceFactory

class SeraphicDeviltryFactory : SourceFactory {
    override fun createSources(): List<Source> = listOf(
        SeraphicDeviltry("en", "https://seraphic-deviltry.com"),
        SeraphicDeviltry("es", "https://spanish.seraphic-deviltry.com"),
    )
}
