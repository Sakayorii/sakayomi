package dev.sakayori.sakayomi.extension.en.erisscans

import dev.sakayori.sakayomi.multisrc.keyoapp.Keyoapp

class ErisScans :
    Keyoapp(
        "Eris Scans",
        "https://erisscans.com",
        "en",
    ) {
    override val descriptionSelector: String = "div.grid > div.overflow-hidden > p"
    override val statusSelector: String = "div[alt=Status]"
    override val authorSelector: String = "div[alt=Author]"
    override val artistSelector: String = "div[alt=Artist]"
    override val typeSelector: String = "div[alt='Series Type']"
}
