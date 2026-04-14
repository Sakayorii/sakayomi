package dev.sakayori.sakayomi.extension.en.vyvymangaorg

import dev.sakayori.sakayomi.multisrc.madara.Madara

class VyvyMangaOrg :
    Madara(
        name = "VyvyManga.org",
        baseUrl = "https://vyvymanga.org",
        lang = "en",
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Always
}
