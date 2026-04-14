package dev.sakayori.sakayomi.extension.ar.scans4u

import dev.sakayori.sakayomi.multisrc.keyoapp.Keyoapp

class Scans4u : Keyoapp("Scans 4u", "https://4uscans.com", "ar") {

    override fun chapterListSelector(): String {
        if (!preferences.showPaidChapters) {
            return "#chapters > a:not(:has(.text-sm span:matches(قادم))):not(:has(img[src*=Coin.svg]))"
        }
        return "#chapters > a:not(:has(.text-sm span:matches(قادم)))"
    }
}
