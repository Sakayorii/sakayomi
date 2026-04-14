package dev.sakayori.sakayomi.extension.tr.holyscans

import dev.sakayori.sakayomi.multisrc.madara.Madara

class HolyScans : Madara("Holy Scans", "https://holyscans.com.tr", "tr") {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true
}
