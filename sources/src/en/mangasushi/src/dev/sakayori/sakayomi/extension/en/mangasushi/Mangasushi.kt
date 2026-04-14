package dev.sakayori.sakayomi.extension.en.mangasushi

import dev.sakayori.sakayomi.multisrc.madara.Madara

class Mangasushi : Madara("Mangasushi", "https://mangasushi.org", "en") {
    override val useNewChapterEndpoint: Boolean = true
}
