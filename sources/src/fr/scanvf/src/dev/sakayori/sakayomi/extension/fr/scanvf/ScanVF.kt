package dev.sakayori.sakayomi.extension.fr.scanvf

import dev.sakayori.sakayomi.multisrc.mmrcms.MMRCMS
import dev.sakayori.sakayomi.source.model.MangasPage
import dev.sakayori.sakayomi.source.model.SManga
import kotlin.math.min

class ScanVF :
    MMRCMS(
        "Scan VF",
        "https://www.scan-vf.net",
        "fr",
        itemPath = "",
        supportsAdvancedSearch = false,
    ) {
    override fun parseSearchDirectory(page: Int): MangasPage {
        val manga = searchDirectory.subList((page - 1) * 24, min(page * 24, searchDirectory.size))
            .map {
                SManga.create().apply {
                    url = "/${it.data}"
                    title = it.value
                    thumbnail_url = guessCover(url, null)
                }
            }
        val hasNextPage = (page + 1) * 24 <= searchDirectory.size

        return MangasPage(manga, hasNextPage)
    }
}
