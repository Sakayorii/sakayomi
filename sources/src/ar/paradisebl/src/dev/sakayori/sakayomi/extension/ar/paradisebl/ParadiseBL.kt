package dev.sakayori.sakayomi.extension.ar.paradisebl

import dev.sakayori.sakayomi.multisrc.madara.Madara

class ParadiseBL :
    Madara(
        "Paradise BL",
        "https://paradise-bl.com",
        "ar",
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Always
    override val useNewChapterEndpoint = true

    override val mangaDetailsSelectorTitle = ".new-post-title h3 a"
}
