package dev.sakayori.sakayomi.extension.en.lhtranslation

import dev.sakayori.sakayomi.multisrc.madara.Madara

class LHTranslation : Madara("LHTranslation", "https://lhtranslation.net", "en") {
    override val versionId = 2
    override val useNewChapterEndpoint = true
}
