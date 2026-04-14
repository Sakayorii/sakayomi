package dev.sakayori.sakayomi.extension.en.manhwatop

import dev.sakayori.sakayomi.multisrc.madara.Madara

class Manhwatop : Madara("Manhwatop", "https://manhwatop.com", "en") {

    // The website does not flag the content.
    override val filterNonMangaItems = false
}
