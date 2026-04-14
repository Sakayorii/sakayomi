package dev.sakayori.sakayomi.extension.all.kemono

import dev.sakayori.sakayomi.multisrc.kemono.Kemono

class Kemono : Kemono("Kemono", "https://kemono.cr", "all") {
    override val getTypes = listOf(
        "Patreon",
        "Pixiv Fanbox",
        "Discord",
        "Fantia",
        "Afdian",
        "Boosty",
        "Gumroad",
        "SubscribeStar",
    )
}
