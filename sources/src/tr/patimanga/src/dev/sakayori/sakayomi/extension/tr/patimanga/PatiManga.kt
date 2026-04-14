package dev.sakayori.sakayomi.extension.tr.patimanga

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class PatiManga :
    MangaThemesia(
        "Pati Manga",
        "https://www.patimanga.com",
        "tr",
        dateFormat = SimpleDateFormat("MMMM d, yyy", Locale("tr")),
    )
