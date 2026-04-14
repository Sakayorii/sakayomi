package dev.sakayori.sakayomi.extension.tr.afroditscans

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class AfroditScans :
    MangaThemesia(
        "Afrodit Scans",
        "https://afroditscans.com",
        "tr",
        dateFormat = SimpleDateFormat("MMMM d, yyy", Locale("tr")),
    )
