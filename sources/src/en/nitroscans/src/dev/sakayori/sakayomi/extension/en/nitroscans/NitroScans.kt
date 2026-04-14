package dev.sakayori.sakayomi.extension.en.nitroscans

import dev.sakayori.sakayomi.multisrc.madara.Madara

class NitroScans : Madara("Nitro Scans", "https://nitroscans.net", "en") {
    override val id = 1310352166897986481

    override val mangaSubString = "mangas"

    override val filterNonMangaItems = false

    override val useLoadMoreRequest = LoadMoreStrategy.Always
    override val useNewChapterEndpoint = true
}
