package dev.sakayori.sakayomi.extension.all.manga18me

import dev.sakayori.sakayomi.source.Source
import dev.sakayori.sakayomi.source.SourceFactory

class M18MFactory : SourceFactory {
    override fun createSources(): List<Source> = listOf(
        Manga18Me("all"),
        Manga18Me("en"),
    )
}
