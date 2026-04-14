package dev.sakayori.sakayomi.extension.es.novamanhwa

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class NovaManhwa :
    MangaThemesia(
        "Nova Manhwas",
        "https://novamanhwa.cc",
        "es",
        dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale("es")),
    )
