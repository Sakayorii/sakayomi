package dev.sakayori.sakayomi.extension.ja.dmm

import dev.sakayori.sakayomi.source.SourceFactory

class DmmFactory : SourceFactory {
    override fun createSources() = listOf(DmmCom(), Fanza())
}

class DmmCom : Dmm() {
    override val name = "DMM"
    override val domain = "book.dmm.com"
    override val shopName = "general"
}

class Fanza : Dmm() {
    override val name = "FANZA"
    override val domain = "book.dmm.co.jp"
    override val shopName = "adult"
}
