package dev.sakayori.sakayomi.extension.id.komikucom

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class KomikuCom : MangaThemesia("Komiku.com", "https://01.komiku.asia", "id", dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale("id")))
