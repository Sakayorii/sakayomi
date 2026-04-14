package dev.sakayori.sakayomi.extension.en.manhwa18org

import dev.sakayori.sakayomi.multisrc.madara.Madara

class Manhwa18Org : Madara("Manhwa18.org", "https://manhwa18.org", "en") {

    // The website does not flag the content.
    override val filterNonMangaItems = false
}
