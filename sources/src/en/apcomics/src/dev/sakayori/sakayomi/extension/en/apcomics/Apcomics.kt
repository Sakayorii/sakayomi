package dev.sakayori.sakayomi.extension.en.apcomics

import dev.sakayori.sakayomi.multisrc.madara.Madara

class Apcomics :
    Madara(
        "AP Comics",
        "https://apcomics.org",
        "en",
    ) {
    override val useNewChapterEndpoint: Boolean = true
}
