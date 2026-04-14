package dev.sakayori.sakayomi.extension.all.holonometria

import dev.sakayori.sakayomi.source.SourceFactory

class HolonometriaFactory : SourceFactory {
    override fun createSources() = listOf(
        Holonometria("ja", ""),
        Holonometria("en"),
        Holonometria("id"),
    )
}
