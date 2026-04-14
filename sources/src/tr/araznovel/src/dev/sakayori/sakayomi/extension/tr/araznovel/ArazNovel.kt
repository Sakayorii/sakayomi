package dev.sakayori.sakayomi.extension.tr.araznovel

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class ArazNovel :
    Madara(
        "ArazNovel",
        "https://araznovel.com",
        "tr",
        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()),
    )
