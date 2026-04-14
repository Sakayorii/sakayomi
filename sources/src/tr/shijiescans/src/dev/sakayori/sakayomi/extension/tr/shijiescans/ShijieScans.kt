package dev.sakayori.sakayomi.extension.tr.shijiescans

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class ShijieScans :
    MangaThemesia(
        "Shijie Scans",
        "https://shijiescans.com",
        "tr",
        "/seri",
        SimpleDateFormat("MMM d, yyy", Locale("tr")),
    )
