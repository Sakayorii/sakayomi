package dev.sakayori.sakayomi.extension.ja.shonenjumpplus

import dev.sakayori.sakayomi.multisrc.gigaviewer.GigaViewer

class ShonenJumpPlus :
    GigaViewer(
        "Shonen Jump+",
        "https://shonenjumpplus.com",
        "ja",
    ) {
    override val searchMangaNextPageSelector = "a.pager-next"

    override fun getCollections(): List<Collection> = listOf(
        Collection("ジャンプ＋連載一覧", ""),
        Collection("ジャンプ＋読切シリーズ", "oneshot"),
        Collection("連載終了作品", "finished"),
    )
}
