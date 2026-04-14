package dev.sakayori.sakayomi.extension.all.yskcomics

import dev.sakayori.sakayomi.source.SourceFactory

class YSKComicsFactory : SourceFactory {
    override fun createSources() = listOf(
        YSKComics("ar"),
        YSKComics("en"),
    )
}
