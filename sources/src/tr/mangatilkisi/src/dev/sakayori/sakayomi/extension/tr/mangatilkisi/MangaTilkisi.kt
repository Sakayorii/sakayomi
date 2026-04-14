package dev.sakayori.sakayomi.extension.tr.mangatilkisi

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.POST
import dev.sakayori.sakayomi.source.model.SChapter
import okhttp3.FormBody
import okhttp3.Request
import java.text.SimpleDateFormat
import java.util.Locale

class MangaTilkisi :
    Madara(
        "MangaTilkisi",
        "https://www.tilkiscans.com",
        "tr",
        dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("tr")),
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Always
    override val useNewChapterEndpoint = true

    override fun pageListRequest(chapter: SChapter): Request {
        val payload = FormBody.Builder()
            .add("verified", "1")
            .build()
        return POST(chapter.url, headers, payload)
    }
}
