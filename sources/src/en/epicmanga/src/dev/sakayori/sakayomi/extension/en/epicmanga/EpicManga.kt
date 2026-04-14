package dev.sakayori.sakayomi.extension.en.epicmanga

import dev.sakayori.sakayomi.multisrc.madara.Madara

class EpicManga : Madara("EpicManga", "https://epicmanga.co", "en") {
    override val useNewChapterEndpoint = true
}
