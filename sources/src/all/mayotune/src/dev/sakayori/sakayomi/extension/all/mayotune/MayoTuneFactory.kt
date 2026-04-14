package dev.sakayori.sakayomi.extension.all.mayotune

import dev.sakayori.sakayomi.source.Source
import dev.sakayori.sakayomi.source.SourceFactory

class MayoTuneFactory : SourceFactory {
    override fun createSources(): List<Source> = listOf(
        MayoTune("en", ""),
        MayoTune("ja", "raw"),
    )
}
