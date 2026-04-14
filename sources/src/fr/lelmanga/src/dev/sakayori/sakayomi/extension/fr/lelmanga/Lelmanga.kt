package dev.sakayori.sakayomi.extension.fr.lelmanga

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class Lelmanga : MangaThemesia("Lelmanga", "https://www.lelmanga.com", "fr", dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)) {
    override val altNamePrefix = "Nom alternatif: "
    override val seriesAuthorSelector = ".imptdt:contains(Auteur) i"
    override val seriesArtistSelector = ".imptdt:contains(Artiste) i"
}
