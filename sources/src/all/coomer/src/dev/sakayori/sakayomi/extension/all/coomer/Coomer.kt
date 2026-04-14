package dev.sakayori.sakayomi.extension.all.coomer

import dev.sakayori.sakayomi.multisrc.kemono.Kemono

class Coomer : Kemono("Coomer", "https://coomer.st", "all") {
    override val getTypes = listOf(
        "OnlyFans",
        "Fansly",
        "CandFans",
    )
}
