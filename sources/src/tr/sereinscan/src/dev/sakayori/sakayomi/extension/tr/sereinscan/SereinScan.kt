package dev.sakayori.sakayomi.extension.tr.sereinscan

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class SereinScan :
    MangaThemesia(
        "Serein Scan",
        "https://sereinscan.com",
        "tr",
        dateFormat = SimpleDateFormat("MMM d, yyy", Locale("tr")),
    )
