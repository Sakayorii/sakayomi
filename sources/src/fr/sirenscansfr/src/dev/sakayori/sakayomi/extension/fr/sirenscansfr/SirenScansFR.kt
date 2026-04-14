package dev.sakayori.sakayomi.extension.fr.sirenscansfr

import dev.sakayori.sakayomi.multisrc.keyoapp.Keyoapp

class SirenScansFR :
    Keyoapp(
        "Siren Scans FR",
        "https://sirenscans.fr",
        "fr",
    ) {
    override fun popularMangaSelector(): String = "section.splide.series-splide a.splide__slide"
}
