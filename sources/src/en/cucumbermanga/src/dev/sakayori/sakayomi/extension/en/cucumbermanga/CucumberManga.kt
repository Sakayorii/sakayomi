package dev.sakayori.sakayomi.extension.en.cucumbermanga

import dev.sakayori.sakayomi.multisrc.madara.Madara

class CucumberManga : Madara("Cucumber Manga", "https://cucumbermanga.com", "en") {
    override val useLoadMoreRequest = LoadMoreStrategy.Always
    override val useNewChapterEndpoint = true
}
