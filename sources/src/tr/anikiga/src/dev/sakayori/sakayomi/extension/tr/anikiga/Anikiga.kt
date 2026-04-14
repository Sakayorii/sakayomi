package dev.sakayori.sakayomi.extension.tr.anikiga

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class Anikiga : Madara("Anikiga", "https://anikiga.com", "tr", SimpleDateFormat("d MMMMM yyyy", Locale("tr")))
