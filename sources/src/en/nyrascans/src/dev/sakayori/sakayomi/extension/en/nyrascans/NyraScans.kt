package dev.sakayori.sakayomi.extension.en.nyrascans

import dev.sakayori.sakayomi.multisrc.keyoapp.Keyoapp

class NyraScans :
    Keyoapp(
        "Nyra Scans",
        "https://nyrascans.com",
        "en",
    ) {
    override val descriptionSelector: String = "div.grid > div.overflow-hidden > p"
    override val statusSelector: String = "div[alt=Status]"
    override val authorSelector: String = "div[alt=Author]"
    override val artistSelector: String = "div[alt=Artist]"
    override val genreSelector: String = "div[alt='Series Type']"
}
