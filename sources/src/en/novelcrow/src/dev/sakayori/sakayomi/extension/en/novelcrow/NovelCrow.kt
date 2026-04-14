package dev.sakayori.sakayomi.extension.en.novelcrow

import dev.sakayori.sakayomi.multisrc.madara.Madara

class NovelCrow : Madara("NovelCrow", "https://novelcrow.com", "en") {
    override val useNewChapterEndpoint = true
}
