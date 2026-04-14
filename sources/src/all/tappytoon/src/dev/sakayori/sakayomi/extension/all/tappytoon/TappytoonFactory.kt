package dev.sakayori.sakayomi.extension.all.tappytoon

import dev.sakayori.sakayomi.source.SourceFactory

class TappytoonFactory : SourceFactory {
    private val langs = setOf("en", "fr", "de")

    override fun createSources() = langs.map(::Tappytoon)
}
