package dev.sakayori.sakayomi.extension.th.popsmanga

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class PopsManga :
    MangaThemesia(
        "PopsManga",
        "https://popsmanga.net",
        "th",
        dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale("th")),
    )
