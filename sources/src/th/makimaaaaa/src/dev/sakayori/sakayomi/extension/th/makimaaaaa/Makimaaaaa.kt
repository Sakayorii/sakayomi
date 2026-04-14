package dev.sakayori.sakayomi.extension.th.makimaaaaa

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class Makimaaaaa :
    MangaThemesia(
        "Makimaaaaa",
        "https://makimaaaaa.com",
        "th",
        dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale("th")),
    ) {
    override val seriesTypeSelector = ".tsinfo .imptdt:contains(ประเภท) a"
    override val seriesStatusSelector = ".tsinfo .imptdt:contains(สถานะ) i"
}
