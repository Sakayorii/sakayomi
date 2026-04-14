package dev.sakayori.sakayomi.extension.fr.yaoiscan

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class YaoiScan :
    MangaThemesia(
        name = "YaoiScan",
        baseUrl = "https://yaoiscan.fr",
        lang = "fr",
        mangaUrlDirectory = "/catalogue",
        dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.FRANCE),
    ) {
    override val seriesStatusSelector = ".status-value"
}
