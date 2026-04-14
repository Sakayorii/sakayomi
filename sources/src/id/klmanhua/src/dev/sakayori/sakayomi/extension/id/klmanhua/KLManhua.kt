package dev.sakayori.sakayomi.extension.id.klmanhua

import dev.sakayori.sakayomi.multisrc.zeistmanga.ZeistManga

class KLManhua : ZeistManga("KLManhua", "https://klmanhua.blogspot.com", "id") {

    override val hasFilters = true
    override val hasLanguageFilter = false
}
