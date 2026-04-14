package dev.sakayori.sakayomi.extension.en.boratscans

import dev.sakayori.sakayomi.multisrc.madara.Madara

class BoratScans :
    Madara(
        "Borat Scans",
        "https://boratscans.com",
        "en",
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true
}
