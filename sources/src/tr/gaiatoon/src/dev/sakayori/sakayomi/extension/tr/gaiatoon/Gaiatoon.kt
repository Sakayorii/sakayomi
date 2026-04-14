package dev.sakayori.sakayomi.extension.tr.gaiatoon

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class Gaiatoon :
    MangaThemesia(
        "Gaiatoon",
        "https://gaiatoon.com",
        "tr",
        dateFormat = SimpleDateFormat("MMMM d, yyy", Locale("tr")),
    )
