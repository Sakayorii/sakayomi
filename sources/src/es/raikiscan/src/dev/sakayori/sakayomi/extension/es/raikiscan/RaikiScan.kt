package dev.sakayori.sakayomi.extension.es.raikiscan

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class RaikiScan : MangaThemesia("Raiki Scan", "https://raikiscan.com", "es", dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale("es")))
