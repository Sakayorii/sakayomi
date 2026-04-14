package dev.sakayori.sakayomi.extension.id.komikdewasa

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class KomikDewasa :
    MangaThemesia(
        "Komik Dewasak",
        "https://komikdewasa.mom",
        "id",
        mangaUrlDirectory = "/komik",
        dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale("id")),
    ) {
    override val hasProjectPage = true
}
