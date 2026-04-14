package dev.sakayori.sakayomi.extension.en.writerscans

import dev.sakayori.sakayomi.multisrc.keyoapp.Keyoapp

class WriterScans :
    Keyoapp(
        "Writer Scans",
        "https://writerscans.com",
        "en",
    ) {
    override fun popularMangaSelector() = "div:contains(Trending) + div .group.overflow-hidden"
}
