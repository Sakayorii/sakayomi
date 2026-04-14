package dev.sakayori.sakayomi.extension.en.manhwamanhua

import dev.sakayori.sakayomi.multisrc.madara.Madara

class ManhwaManhua : Madara("ManhwaManhua", "https://manhwamanhua.com", "en") {
    override val useNewChapterEndpoint = true
    override val filterNonMangaItems = false
}
