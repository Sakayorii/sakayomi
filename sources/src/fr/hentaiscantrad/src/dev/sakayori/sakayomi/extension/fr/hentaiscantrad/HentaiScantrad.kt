package dev.sakayori.sakayomi.extension.fr.hentaiscantrad

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class HentaiScantrad : Madara("Hentai-Scantrad", "https://hentai.scantrad-vf.cc", "fr", dateFormat = SimpleDateFormat("d MMMM, yyyy", Locale.FRENCH)) {
    override val mangaDetailsSelectorStatus = "div.summary-heading:contains(État) + .summary-content"
}
