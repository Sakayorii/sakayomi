package dev.sakayori.sakayomi.extension.id.okyykomik

import dev.sakayori.sakayomi.multisrc.zeistmanga.ZeistManga
import dev.sakayori.sakayomi.source.model.MangasPage
import okhttp3.Request
import okhttp3.Response

class OkyyKomik : ZeistManga("OkyyKomik", "https://www.okyykomik.my.id", "id") {

    override val supportsLatest = false

    override fun popularMangaRequest(page: Int): Request = latestUpdatesRequest(page)
    override fun popularMangaParse(response: Response): MangasPage = latestUpdatesParse(response)
}
