package dev.sakayori.sakayomi.extension.all.luscious

import dev.sakayori.sakayomi.source.Source
import dev.sakayori.sakayomi.source.SourceFactory

class LusciousFactory : SourceFactory {
    override fun createSources(): List<Source> = listOf(
        LusciousEN(),
        LusciousJA(),
        LusciousES(),
        LusciousIT(),
        LusciousDE(),
        LusciousFR(),
        LusciousZH(),
        LusciousKO(),
        LusciousOTHER(),
        LusciousPT(),
        LusciousTH(),
        LusciousALL(),
    )
}

class LusciousEN : Luscious("en")
class LusciousJA : Luscious("ja")
class LusciousES : Luscious("es")
class LusciousIT : Luscious("it")
class LusciousDE : Luscious("de")
class LusciousFR : Luscious("fr")
class LusciousZH : Luscious("zh")
class LusciousKO : Luscious("ko")
class LusciousOTHER : Luscious("other")
class LusciousPT : Luscious("pt-BR") {
    // Hardcode the id because the language wasn't specific.
    override val id: Long = 5826725746643311801
}

class LusciousTH : Luscious("th")
class LusciousALL : Luscious("all")
