package dev.sakayori.sakayomi.extension.all.hennojin

import dev.sakayori.sakayomi.source.SourceFactory

class HennojinFactory : SourceFactory {
    override fun createSources() = listOf(
        Hennojin("en"),
        Hennojin("ja"),
    )
}
