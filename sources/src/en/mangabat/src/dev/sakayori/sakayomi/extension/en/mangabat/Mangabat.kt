package dev.sakayori.sakayomi.extension.en.mangabat

import dev.sakayori.sakayomi.multisrc.mangabox.MangaBox
import dev.sakayori.sakayomi.source.model.SManga
import okhttp3.Request

class Mangabat :
    MangaBox(
        "Mangabat",
        arrayOf(
            "www.mangabats.com",
        ),
        "en",
    ) {
    override fun mangaDetailsRequest(manga: SManga): Request {
        if (manga.url.contains("mangabat.com/")) {
            throw Exception(MIGRATE_MESSAGE)
        }
        return super.mangaDetailsRequest(manga)
    }
    companion object {
        private const val MIGRATE_MESSAGE = "Migrate this entry from \"Mangabat\" to \"Mangabat\" to continue reading"
    }
}
