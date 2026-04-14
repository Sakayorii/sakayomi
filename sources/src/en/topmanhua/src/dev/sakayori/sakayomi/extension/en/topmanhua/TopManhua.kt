package dev.sakayori.sakayomi.extension.en.topmanhua

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.POST
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.util.asJsoup
import rx.Observable
import java.text.SimpleDateFormat
import java.util.Locale

class TopManhua : Madara("Top Manhua", "https://mangatop.org", "en", SimpleDateFormat("MM/dd/yy", Locale.US)) {
    override val client = super.client.newBuilder()
        .rateLimit(2)
        .build()

    // The website does not flag the content.
    override val filterNonMangaItems = false

    override val mangaSubString = "manhua"

    // ============================== Chapters ==============================
    override fun fetchChapterList(manga: SManga): Observable<List<SChapter>> = Observable.fromCallable { fetchAllChapters(manga) }

    private fun fetchAllChapters(manga: SManga): List<SChapter> {
        val chapters = mutableListOf<SChapter>()
        var page = 1
        while (true) {
            val response = client.newCall(POST("${getMangaUrl(manga)}ajax/chapters/?t=${page++}", xhrHeaders)).execute()
            val document = response.asJsoup()
            val currentPage = document.select(chapterListSelector())
                .map(::chapterFromElement)

            chapters += currentPage
            response.close()

            if (currentPage.isEmpty()) {
                return chapters
            }
        }
    }
}
