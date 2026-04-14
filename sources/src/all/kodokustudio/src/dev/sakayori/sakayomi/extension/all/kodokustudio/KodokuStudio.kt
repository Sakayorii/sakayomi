package dev.sakayori.sakayomi.extension.all.kodokustudio

import dev.sakayori.sakayomi.multisrc.madara.Madara

class KodokuStudio :
    Madara(
        "Kodoku Studio",
        "https://kodokustudio.com",
        "all",
    ) {
    override val useNewChapterEndpoint = true
    override val mangaSubString = "manhua"
}
