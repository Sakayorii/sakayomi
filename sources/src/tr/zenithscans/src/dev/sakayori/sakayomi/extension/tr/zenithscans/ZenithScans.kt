package dev.sakayori.sakayomi.extension.tr.zenithscans

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class ZenithScans :
    MangaThemesia(
        "Zenith Scans",
        "https://zenithscans.com",
        "tr",
        dateFormat = SimpleDateFormat("MMM d, yyy", Locale("tr")),
    )
