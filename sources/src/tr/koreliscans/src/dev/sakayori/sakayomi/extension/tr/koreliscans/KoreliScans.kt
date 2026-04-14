package dev.sakayori.sakayomi.extension.tr.koreliscans

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class KoreliScans :
    MangaThemesia(
        "Koreli Scans",
        "https://nabimanga.com",
        "tr",
        dateFormat = SimpleDateFormat("d MMMM yyyy", Locale("tr")),
    )
