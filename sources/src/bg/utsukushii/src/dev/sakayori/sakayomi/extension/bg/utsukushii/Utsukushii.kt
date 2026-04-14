package dev.sakayori.sakayomi.extension.bg.utsukushii

import dev.sakayori.sakayomi.multisrc.mmrcms.MMRCMS
import dev.sakayori.sakayomi.network.GET
import okhttp3.Request

class Utsukushii : MMRCMS("Utsukushii", "https://utsukushii-bg.com", "bg") {
    override fun popularMangaRequest(page: Int): Request = GET("$baseUrl/manga-list", headers)
}
