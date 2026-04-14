package dev.sakayori.sakayomi.extension.all.coronaex

import dev.sakayori.sakayomi.source.SourceFactory

class CoronaExFactory : SourceFactory {
    override fun createSources() = listOf(
        CoronaEx("ja", "to-corona-ex.com"),
        CoronaEx("en", "en.to-corona-ex.com"),
    )
}
