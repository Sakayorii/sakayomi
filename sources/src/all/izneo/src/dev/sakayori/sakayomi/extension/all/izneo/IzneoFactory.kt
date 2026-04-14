package dev.sakayori.sakayomi.extension.all.izneo

import dev.sakayori.sakayomi.source.SourceFactory

class IzneoFactory : SourceFactory {
    override fun createSources() = listOf(
        Izneo("en"),
        Izneo("fr"),
        // Izneo("de"),
        // Izneo("nl"),
        // Izneo("it"),
    )
}
