package dev.sakayori.sakayomi.extension.id.manhwalistorg

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class ManhwalistOrg :
    MangaThemesia(
        "Manhwalist.org",
        "https://isekaikomik.com",
        "id",
        dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale("id")),
    )
