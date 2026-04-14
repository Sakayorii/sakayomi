package dev.sakayori.sakayomi.extension.en.hentaixdickgirl

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.source.model.UpdateStrategy
import org.jsoup.nodes.Document

class HentaiXDickgirl : Madara("HentaiXDickgirl", "https://hentaixdickgirl.com", "en") {

    override fun mangaDetailsParse(document: Document): SManga = super.mangaDetailsParse(document).apply {
        update_strategy = UpdateStrategy.ONLY_FETCH_ONCE
    }
}
