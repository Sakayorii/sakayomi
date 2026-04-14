package dev.sakayori.sakayomi.extension.all.simplyhentai

import dev.sakayori.sakayomi.source.SourceFactory

class SimplyHentaiFactory : SourceFactory {
    override fun createSources() = listOf(
        SimplyHentai("en", "english"),
        SimplyHentai("ja", "japanese"),
        SimplyHentai("zh", "chinese"),
        SimplyHentai("ko", "korean"),
        SimplyHentai("es", "spanish"),
        SimplyHentai("ru", "russian"),
        SimplyHentai("fr", "french"),
        SimplyHentai("de", "german"),
        SimplyHentai("it", "italian"),
        SimplyHentai("pl", "polish"),
    )
}
