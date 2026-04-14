package dev.sakayori.sakayomi.extension.en.mangareadorg

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class MangaReadOrg :
    Madara(
        "MangaRead.org",
        "https://www.mangaread.org",
        "en",
        SimpleDateFormat("dd.MM.yyy", Locale.US),
    )
