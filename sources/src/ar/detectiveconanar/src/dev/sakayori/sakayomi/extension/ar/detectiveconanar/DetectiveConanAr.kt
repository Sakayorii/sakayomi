package dev.sakayori.sakayomi.extension.ar.detectiveconanar

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class DetectiveConanAr :
    Madara(
        "شبكة كونان العربية",
        "https://manga.detectiveconanar.com",
        "ar",
        SimpleDateFormat("MMMM dd, yyyy", Locale("ar")),
    )
