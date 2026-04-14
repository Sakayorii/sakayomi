package dev.sakayori.sakayomi.extension.ja.mangacross

import dev.sakayori.sakayomi.multisrc.comiciviewer.ComiciViewer

// MangaCross became ChampionCross
class MangaCross :
    ComiciViewer(
        "Champion Cross",
        "https://championcross.jp",
        "ja",
    ) {
    override val versionId = 2
}
