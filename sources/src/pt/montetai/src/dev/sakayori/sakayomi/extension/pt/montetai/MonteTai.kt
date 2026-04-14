package dev.sakayori.sakayomi.extension.pt.montetai

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.network.POST
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.util.asJsoup
import Sakayorii.utils.parseAs
import okhttp3.FormBody
import okhttp3.Request
import rx.Observable
import java.text.SimpleDateFormat
import java.util.Locale

class MonteTai :
    Madara(
        "Monte Tai",
        "https://montetaiscanlator.xyz",
        "pt-BR",
        SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR")),
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(3, 1)
        .build()

    override val popularMangaUrlSelector = ".mt-manga-catalog-card__title a"

    override fun popularMangaSelector() = ".mt-manga-catalog-card"

    override val mangaDetailsSelectorThumbnail = ".mtx-cover img"
    override val mangaDetailsSelectorAuthor = ".mtx-side-item:contains(Autor) .mtx-side-value"
    override val mangaDetailsSelectorArtist = ".mtx-side-item:contains(Artista) .mtx-side-value"
    override val mangaDetailsSelectorGenre = ".mtx-chip-list a"
    override val mangaDetailsSelectorDescription = ".mtx-synopsis"
    override val mangaDetailsSelectorStatus = ".mtx-pill-status"

    override fun chapterListRequest(manga: SManga): Request {
        val document = client.newCall(GET(getMangaUrl(manga), headers)).execute().asJsoup()
        val script = document.selectFirst("#mt-header-js-js-extra")!!.data()

        val nonce = NONCE_REGEX.find(script)!!.groupValues.last()
        val mangaId = document.selectFirst("a[data-post]")!!.attr("data-post")

        val body = FormBody.Builder()
            .add("action", "mt_get_summary_chapters")
            .add("nonce", nonce)
            .add("manga_id", mangaId)
            .build()

        val chapterHeaders = headers.newBuilder()
            .set("X-Requested-With", "XMLHttpRequest")
            .build()

        return POST("$baseUrl/wp-admin/admin-ajax.php", chapterHeaders, body)
    }

    override fun fetchChapterList(manga: SManga) = Observable.fromCallable {
        client.newCall(chapterListRequest(manga)).execute()
            .body.string()
            .parseAs<ChapterListDto>()
            .toSChapterList(::parseChapterDate)
    }

    companion object {
        val NONCE_REGEX = """(?:nonce"[^"]+")([^"]+)""".toRegex()
    }
}
