package dev.sakayori.sakayomi.extension.en.mangahe

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.source.model.Page
import okhttp3.Response

class MangaHe : Madara("MangaHe", "https://mangahe.com", "en") {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = false

    // Skip self-promotion
    override fun pageListParse(response: Response): List<Page> = super.pageListParse(response).filterIndexed { idx, page ->
        !(idx == 0 && page.imageUrl?.endsWith("/1-000001.jpg") == true)
    }
}
