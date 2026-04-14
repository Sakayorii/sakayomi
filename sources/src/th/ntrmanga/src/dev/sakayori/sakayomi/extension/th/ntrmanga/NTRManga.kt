package dev.sakayori.sakayomi.extension.th.ntrmanga

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class NTRManga :
    MangaThemesia(
        "NTR-Manga",
        "https://www.ntr-manga.net",
        "th",
        dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale("th")),
    )
