package dev.sakayori.sakayomi.extension.ar.despairmanga

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.source.model.Page
import okhttp3.Request

class DespairManga :
    MangaThemesia(
        "Despair Manga",
        "https://despair-manga.net",
        "ar",
    ) {

    override fun imageRequest(page: Page): Request {
        page.imageUrl = page.imageUrl!!.let {
            if (it.startsWith("http")) it else baseUrl + it
        }
        val newHeaders = headersBuilder()
            .set("Accept", "image/avif,image/webp,image/png,image/jpeg,*/*")
            .set("Referer", page.url)
            .build()

        return GET(page.imageUrl!!, newHeaders)
    }
}
