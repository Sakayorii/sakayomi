package dev.sakayori.sakayomi.extension.en.ksgroupscans

import dev.sakayori.sakayomi.multisrc.madara.Madara

class KSGroupScans : Madara("KSGroupScans", "https://ksgroupscans.com", "en") {
    override val versionId = 2
    override val useNewChapterEndpoint = true
}
