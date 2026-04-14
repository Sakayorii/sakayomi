package dev.sakayori.sakayomi.extension.en.decadencescans

import dev.sakayori.sakayomi.multisrc.madara.Madara

class DecadenceScans : Madara("Decadence Scans", "https://reader.decadencescans.com", "en") {
    override val useNewChapterEndpoint: Boolean = true
}
