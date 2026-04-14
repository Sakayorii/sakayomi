package dev.sakayori.sakayomi.extension.tr.tarotscans

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class TarotScans : MangaThemesia("Tarot Scans", "https://www.tarotscans.com", "tr", dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale("tr")))
