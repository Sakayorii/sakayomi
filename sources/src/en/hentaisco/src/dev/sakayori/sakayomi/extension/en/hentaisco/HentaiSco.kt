package dev.sakayori.sakayomi.extension.en.hentaisco

import dev.sakayori.sakayomi.multisrc.madara.Madara

class HentaiSco : Madara("HentaiSco", "https://hentaisco.cc", "en") {
    override val mangaSubString = "hentai"
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = false
}
