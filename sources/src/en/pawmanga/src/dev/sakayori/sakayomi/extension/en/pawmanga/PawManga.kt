package dev.sakayori.sakayomi.extension.en.pawmanga

import dev.sakayori.sakayomi.multisrc.madara.Madara

class PawManga : Madara("Paw Manga", "https://pawmanga.com", "en") {
    override val useNewChapterEndpoint = true
}
