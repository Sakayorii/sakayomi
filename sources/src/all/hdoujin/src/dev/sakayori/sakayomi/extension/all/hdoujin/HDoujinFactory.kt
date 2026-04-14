package dev.sakayori.sakayomi.extension.all.hdoujin

import dev.sakayori.sakayomi.source.SourceFactory

class HDoujinFactory : SourceFactory {
    override fun createSources() = listOf(
        HDoujin("all"),
        HDoujin("en", "english"),
        HDoujin("es", "spanish"),
        HDoujin("ja", "japanese"),
        HDoujin("kr", "korean"),
        HDoujin("zh", "chinese"),
    )
}
