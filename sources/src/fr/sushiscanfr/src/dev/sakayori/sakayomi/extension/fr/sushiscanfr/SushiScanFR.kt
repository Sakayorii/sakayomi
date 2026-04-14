package dev.sakayori.sakayomi.extension.fr.sushiscanfr

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.source.model.SManga
import org.jsoup.nodes.Document
import java.text.SimpleDateFormat
import java.util.Locale

class SushiScanFR : MangaThemesia("Sushiscan.fr", "https://sushiscan.fr", "fr", mangaUrlDirectory = "/catalogue", dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.FRENCH)) {
    override val altNamePrefix = "Nom alternatif : "
    override val seriesAuthorSelector = ".imptdt:contains(Auteur) i, .fmed b:contains(Auteur)+span"
    override val seriesStatusSelector = ".imptdt:contains(Statut) i"
    override fun String?.parseStatus(): Int = when {
        this == null -> SManga.UNKNOWN
        this.contains("En Cours", ignoreCase = true) -> SManga.ONGOING
        this.contains("Terminé", ignoreCase = true) -> SManga.COMPLETED
        else -> SManga.UNKNOWN
    }

    override fun mangaDetailsParse(document: Document): SManga = super.mangaDetailsParse(document).apply {
        status = document.select(seriesStatusSelector).text().parseStatus()
    }
}
