package dev.sakayori.sakayomi.extension.id.kanzenin

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class Kanzenin :
    MangaThemesia(
        "Kanzenin",
        "https://kanzenin.info",
        "id",
        dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale("id")),
    )
