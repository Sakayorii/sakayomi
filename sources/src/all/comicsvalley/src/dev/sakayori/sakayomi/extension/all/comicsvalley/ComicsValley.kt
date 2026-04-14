package dev.sakayori.sakayomi.extension.all.comicsvalley

import dev.sakayori.sakayomi.multisrc.madara.Madara

class ComicsValley :
    Madara(
        "Comics Valley",
        "https://comicsvalley.com",
        "all",
    ) {
    override val mangaSubString = "comics-new"
    override val useNewChapterEndpoint = true
    override val useLoadMoreRequest = LoadMoreStrategy.Always
    override val id = 1103204227230640533
}
