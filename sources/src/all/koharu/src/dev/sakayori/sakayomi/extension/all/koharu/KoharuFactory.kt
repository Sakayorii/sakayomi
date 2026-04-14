package dev.sakayori.sakayomi.extension.all.koharu

import dev.sakayori.sakayomi.source.Source
import dev.sakayori.sakayomi.source.SourceFactory

class KoharuFactory : SourceFactory {
    override fun createSources(): List<Source> = listOf(
        Koharu(),
        Koharu("en", "english"),
        Koharu("ja", "japanese"),
        Koharu("zh", "chinese"),
    )
}
