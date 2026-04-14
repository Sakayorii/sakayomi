package dev.sakayori.sakayomi.extension.en.wuxiaworld

import dev.sakayori.sakayomi.multisrc.madara.Madara

class WuxiaWorld : Madara("WuxiaWorld", "https://wuxiaworld.site", "en") {
    override val mangaSubString = "novel"
    override val useNewChapterEndpoint = true
}
