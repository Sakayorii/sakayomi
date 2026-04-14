package dev.sakayori.sakayomi.extension.en.mangatrend

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class MangaTrend :
    MangaThemesia(
        "Manga Trend",
        "https://mangatrend.org",
        "en",
        dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale("ar")),
    )
