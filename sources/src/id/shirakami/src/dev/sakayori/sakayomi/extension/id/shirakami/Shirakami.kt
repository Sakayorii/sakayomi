package dev.sakayori.sakayomi.extension.id.shirakami

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class Shirakami :
    MangaThemesia(
        "Shirakami",
        "https://shirakami.xyz",
        "id",
        dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale("id")),
    )
