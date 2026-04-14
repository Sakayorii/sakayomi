package dev.sakayori.sakayomi.extension.all.xkcd

import dev.sakayori.sakayomi.extension.all.xkcd.translations.XkcdES
import dev.sakayori.sakayomi.extension.all.xkcd.translations.XkcdFR
import dev.sakayori.sakayomi.extension.all.xkcd.translations.XkcdRU
import dev.sakayori.sakayomi.extension.all.xkcd.translations.XkcdZH
import dev.sakayori.sakayomi.source.SourceFactory

class XkcdFactory : SourceFactory {
    override fun createSources() = listOf(
        Xkcd("https://xkcd.com", "en"),
        XkcdES(),
        XkcdZH(),
        XkcdFR(),
        XkcdRU(),
    )
}
