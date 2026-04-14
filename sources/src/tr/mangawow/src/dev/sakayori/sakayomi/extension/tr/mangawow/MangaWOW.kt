package dev.sakayori.sakayomi.extension.tr.mangawow

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class MangaWOW :
    Madara(
        "MangaWOW",
        "https://mangawow.org",
        "tr",
        SimpleDateFormat("MMMM dd, yyyy", Locale("tr")),
    )
