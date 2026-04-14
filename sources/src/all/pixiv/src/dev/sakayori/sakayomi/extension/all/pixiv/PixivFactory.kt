package dev.sakayori.sakayomi.extension.all.pixiv

import dev.sakayori.sakayomi.source.Source
import dev.sakayori.sakayomi.source.SourceFactory

class PixivFactory : SourceFactory {
    override fun createSources(): List<Source> = KNOWN_LOCALES.map { lang -> Pixiv(lang) }
}
