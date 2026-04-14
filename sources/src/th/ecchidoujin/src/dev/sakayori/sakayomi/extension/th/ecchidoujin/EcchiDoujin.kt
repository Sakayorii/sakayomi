package dev.sakayori.sakayomi.extension.th.ecchidoujin

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class EcchiDoujin :
    MangaThemesia(
        "Ecchi-Doujin",
        "https://ecchi-doujin.com",
        "th",
        "/doujin",
        SimpleDateFormat("MMMM d, yyyy", Locale("th")),
    )
