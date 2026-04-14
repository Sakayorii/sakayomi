package dev.sakayori.sakayomi.extension.all.taddyink

import dev.sakayori.sakayomi.source.Source
import dev.sakayori.sakayomi.source.SourceFactory

class TaddyInkFactory : SourceFactory {
    override fun createSources(): List<Source> = listOf(
        TaddyInk("all", ""),
    )
}
