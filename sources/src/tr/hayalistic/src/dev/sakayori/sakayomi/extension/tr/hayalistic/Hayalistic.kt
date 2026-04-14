package dev.sakayori.sakayomi.extension.tr.hayalistic

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class Hayalistic :
    Madara(
        "Hayalistic",
        "https://hayalistic.net",
        "tr",
        dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH),
    )
