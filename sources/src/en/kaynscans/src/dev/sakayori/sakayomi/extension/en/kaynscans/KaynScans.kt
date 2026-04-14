package dev.sakayori.sakayomi.extension.en.kaynscans

import dev.sakayori.sakayomi.multisrc.keyoapp.Keyoapp

class KaynScans :
    Keyoapp(
        "Kayn Scans",
        "https://kaynscan.com",
        "en",
    ) {
    override val descriptionSelector: String = "div.grid > div.overflow-hidden > p"
    override val statusSelector: String = "div[alt=Status]"
    override val authorSelector: String = "div[alt=Author]"
    override val artistSelector: String = "div[alt=Artist]"
    override val typeSelector: String = "div[alt='Series Type']"
}
