package dev.sakayori.sakayomi.extension.all.manhwaclubnet

import dev.sakayori.sakayomi.source.SourceFactory

class ManhwaClubNetFactory : SourceFactory {
    // Font for icon: Cooper BT Std Black Headline
    override fun createSources() = listOf(
        ManhwaClubNet("en"),
        ManhwaClubNet("ko"),
    )
}
