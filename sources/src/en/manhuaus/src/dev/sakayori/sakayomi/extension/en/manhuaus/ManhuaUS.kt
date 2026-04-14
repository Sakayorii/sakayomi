package dev.sakayori.sakayomi.extension.en.manhuaus

import dev.sakayori.sakayomi.multisrc.madara.Madara

class ManhuaUS : Madara("ManhuaUS", "https://manhuaus.com", "en") {

    override val useNewChapterEndpoint: Boolean = true

    // The website does not flag the content.
    override val filterNonMangaItems = false
}
