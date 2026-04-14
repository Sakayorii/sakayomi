package dev.sakayori.sakayomi.extension.en.hentaixyuri

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class HentaiXYuri :
    Madara(
        "HentaiXYuri",
        "https://hentaixyuri.com",
        "en",
        dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.US),
    )
