package dev.sakayori.sakayomi.extension.en.keenspot

import dev.sakayori.sakayomi.source.Source
import dev.sakayori.sakayomi.source.SourceFactory

class KeenspotFactory : SourceFactory {
    override fun createSources(): List<Source> = listOf(TwoKinds())
}
