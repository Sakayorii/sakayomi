package dev.sakayori.sakayomi.extension.en.mangaowlio

import dev.sakayori.sakayomi.multisrc.madara.Madara

class MangaOwlIo :
    Madara(
        "MangaOwl.io (unoriginal)",
        "https://mangaowl.io",
        "en",
    ) {
    override val mangaSubString = "read-1"

    override val useNewChapterEndpoint = true
}
