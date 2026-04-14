package dev.sakayori.sakayomi.extension.all.cubari

import dev.sakayori.sakayomi.source.Source
import dev.sakayori.sakayomi.source.SourceFactory

class CubariFactory : SourceFactory {
    override fun createSources(): List<Source> = listOf(
        Cubari("en"),
        Cubari("all"),
        Cubari("other"),
    )
}
