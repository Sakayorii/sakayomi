package dev.sakayori.sakayomi.extension.en.aryascans

import dev.sakayori.sakayomi.multisrc.madara.Madara

class AryaScans :
    Madara(
        "Arya Scans",
        "https://brainrotcomics.com",
        "en",
    ) {
    override val useNewChapterEndpoint = true

    override val popularMangaUrlSelector = "${super.popularMangaUrlSelector}:not([href=New]):not([target=_self])"
}
