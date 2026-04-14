package dev.sakayori.sakayomi.extension.en.manhuanext

import dev.sakayori.sakayomi.multisrc.madara.Madara

class Manhuanext :
    Madara(
        "Manhuanext",
        "https://manhuanext.com",
        "en",
    ) {
    override val useNewChapterEndpoint = true
}
