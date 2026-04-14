package dev.sakayori.sakayomi.extension.th.lamimanga

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class LamiManga :
    MangaThemesia(
        "Lami-Manga",
        "https://mangalami.com",
        "th",
        dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale("th")),
    )
