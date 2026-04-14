package dev.sakayori.sakayomi.extension.th.tanukimanga

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class TanukiManga :
    MangaThemesia(
        "Tanuki-Manga",
        "https://www.tanuki-manga.net",
        "th",
        dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale("th")),
    )
