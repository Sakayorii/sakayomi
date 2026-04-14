package dev.sakayori.sakayomi.extension.fr.pantheonscan

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class PantheonScan : Madara("Pantheon Scan", "https://pantheon-scan.com", "fr", dateFormat = SimpleDateFormat("d MMMM yyyy", Locale.FRANCE)) {
    override val useNewChapterEndpoint = true
}
