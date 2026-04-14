package dev.sakayori.sakayomi.extension.it.phoenixscans

import dev.sakayori.sakayomi.multisrc.pizzareader.PizzaReader

class PhoenixScans : PizzaReader("Phoenix Scans", "https://www.phoenixscans.com", "it") {
    override val versionId = 2
}
