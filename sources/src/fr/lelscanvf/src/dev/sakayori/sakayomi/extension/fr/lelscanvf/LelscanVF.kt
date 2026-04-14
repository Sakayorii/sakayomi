package dev.sakayori.sakayomi.extension.fr.lelscanvf

import dev.sakayori.sakayomi.multisrc.fuzzydoodle.FuzzyDoodle

class LelscanVF : FuzzyDoodle("Lelscan-VF", "https://lelscanfr.com", "fr") {

    // mmrcms -> FuzzyDoodle
    override val versionId = 2

    override val latestFromHomePage = true
}
