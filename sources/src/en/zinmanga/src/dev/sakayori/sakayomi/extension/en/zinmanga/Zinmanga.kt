package dev.sakayori.sakayomi.extension.en.zinmanga

import dev.sakayori.sakayomi.multisrc.madara.Madara

class Zinmanga : Madara("Zinmanga", "https://mangazin.org", "en") {

    // The website does not flag the content consistently.
    override val filterNonMangaItems = false
}
