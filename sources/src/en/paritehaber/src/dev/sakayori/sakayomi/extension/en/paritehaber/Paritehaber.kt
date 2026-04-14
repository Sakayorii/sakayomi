package dev.sakayori.sakayomi.extension.en.paritehaber

import dev.sakayori.sakayomi.multisrc.madara.Madara

class Paritehaber :
    Madara(
        "Paritehaber",
        "https://www.paritehaber.com",
        "en",
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = false

    override val mangaSubString = "ph"

    override val fetchGenres = false
    override val chapterUrlSuffix = ""
}
