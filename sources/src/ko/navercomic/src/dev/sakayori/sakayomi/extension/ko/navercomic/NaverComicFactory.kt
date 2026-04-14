package dev.sakayori.sakayomi.extension.ko.navercomic

import dev.sakayori.sakayomi.source.Source
import dev.sakayori.sakayomi.source.SourceFactory

class NaverComicFactory : SourceFactory {
    override fun createSources(): List<Source> = listOf(
        NaverWebtoon(),
        NaverBestChallenge(),
        NaverChallenge(),
    )
}
