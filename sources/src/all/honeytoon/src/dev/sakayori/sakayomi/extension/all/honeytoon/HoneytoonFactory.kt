package dev.sakayori.sakayomi.extension.all.honeytoon

import dev.sakayori.sakayomi.source.SourceFactory

class HoneytoonFactory : SourceFactory {
    override fun createSources() = languageList.map(::Honeytoon)
}

class Language(
    val lang: String,
    val langPath: String = lang,
)

private val languageList = listOf(
    Language("de"),
    Language("en"),
    Language("es"),
    Language("fr"),
    Language("it"),
    Language("pt-BR", "pt"),
)
