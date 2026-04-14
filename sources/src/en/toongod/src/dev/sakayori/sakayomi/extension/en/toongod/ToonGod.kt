package dev.sakayori.sakayomi.extension.en.toongod

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class ToonGod : Madara("ToonGod", "https://www.toongod.org", "en", SimpleDateFormat("d MMM yyyy", Locale.US)) {
    override val mangaSubString = "webtoons"
    override val useNewChapterEndpoint = false
}
