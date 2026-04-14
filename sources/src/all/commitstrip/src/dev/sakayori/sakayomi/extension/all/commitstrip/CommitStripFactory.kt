package dev.sakayori.sakayomi.extension.all.commitstrip

import dev.sakayori.sakayomi.source.Source
import dev.sakayori.sakayomi.source.SourceFactory

class CommitStripFactory : SourceFactory {
    override fun createSources(): List<Source> = listOf(
        CommitStripEnglish(),
        CommitStripFrench(),
    )
}

class CommitStripEnglish : CommitStrip("en", "en")
class CommitStripFrench : CommitStrip("fr", "fr")
