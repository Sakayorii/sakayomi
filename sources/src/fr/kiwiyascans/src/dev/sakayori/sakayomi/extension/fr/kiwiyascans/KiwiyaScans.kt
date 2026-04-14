package dev.sakayori.sakayomi.extension.fr.kiwiyascans

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class KiwiyaScans :
    MangaThemesia(
        "Kiwiya Scans",
        "https://kiwiyascans.com",
        "fr",
        dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH),
    ) {
    override fun chapterListSelector() = "ul li:has(div.chbox:not(:has(> span.mcl-price-num))):has(div.eph-num)"
}
