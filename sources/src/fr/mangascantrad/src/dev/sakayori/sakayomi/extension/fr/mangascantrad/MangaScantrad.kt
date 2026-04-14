package dev.sakayori.sakayomi.extension.fr.mangascantrad

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class MangaScantrad : Madara("Manga-Scantrad", "https://manga-scantrad.io", "fr", SimpleDateFormat("d MMM yyyy", Locale.FRANCE)) {
    override val useNewChapterEndpoint: Boolean = true
}
