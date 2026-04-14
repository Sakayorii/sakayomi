package dev.sakayori.sakayomi.extension.en.whalemanga

import dev.sakayori.sakayomi.multisrc.madara.Madara

class WhaleManga : Madara("WhaleManga", "https://whalemanga.com", "en") {
    override val useNewChapterEndpoint = true
}
