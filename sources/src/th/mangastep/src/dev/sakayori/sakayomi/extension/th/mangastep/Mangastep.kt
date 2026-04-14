package dev.sakayori.sakayomi.extension.th.mangastep

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class Mangastep :
    MangaThemesia(
        "Mangastep",
        "https://mangastep.com",
        "th",
        dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale("th")),
    )
