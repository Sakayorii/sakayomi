package dev.sakayori.sakayomi.extension.all.comicfury

import dev.sakayori.sakayomi.source.Source
import dev.sakayori.sakayomi.source.SourceFactory

class ComicFuryFactory : SourceFactory {
    override fun createSources(): List<Source> = listOf(
        ComicFury("all"),
        ComicFury("en"),
        ComicFury("es"),
        ComicFury("pt-BR", "pt"),
        ComicFury("de"),
        ComicFury("fr"),
        ComicFury("it"),
        ComicFury("pl"),
        ComicFury("ja"),
        ComicFury("zh"),
        ComicFury("ru"),
        ComicFury("fi"),
        ComicFury("other"),
        ComicFury("other", "notext", " (No Text)"),
    )
}
