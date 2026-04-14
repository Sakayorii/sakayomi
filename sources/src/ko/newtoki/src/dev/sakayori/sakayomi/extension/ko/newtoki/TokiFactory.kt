package dev.sakayori.sakayomi.extension.ko.newtoki

import dev.sakayori.sakayomi.source.SourceFactory

class TokiFactory : SourceFactory {
    override fun createSources() = listOf(ManaToki, NewTokiWebtoon)
}
