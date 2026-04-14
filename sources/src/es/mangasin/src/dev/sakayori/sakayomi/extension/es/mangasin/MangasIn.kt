package dev.sakayori.sakayomi.extension.es.mangasin

import android.util.Base64
import dev.sakayori.sakayomi.multisrc.mmrcms.MMRCMS
import dev.sakayori.sakayomi.multisrc.mmrcms.SuggestionDto
import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.network.interceptor.rateLimitHost
import dev.sakayori.sakayomi.source.model.FilterList
import dev.sakayori.sakayomi.source.model.MangasPage
import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.util.asJsoup
import Sakayorii.lib.cryptoaes.CryptoAES
import Sakayorii.lib.synchrony.Deobfuscator
import kotlinx.serialization.decodeFromString
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Request
import okhttp3.Response
import org.jsoup.nodes.Document
import java.net.URLDecoder
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class MangasIn :
    MMRCMS(
        "Mangas.in",
        "https://m440.in",
        "es",
        supportsAdvancedSearch = false,
        dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US),
    ) {
    override val client = super.client.newBuilder()
        .rateLimitHost(baseUrl.toHttpUrl(), 1, 1)
        .build()

    override fun headersBuilder() = super.headersBuilder()
        .add("Referer", "$baseUrl/")

    override fun latestUpdatesRequest(page: Int) = GET("$baseUrl/lasted?p=$page", headers)

    override fun latestUpdatesParse(response: Response): MangasPage {
        runCatching { fetchFilterOptions() }

        val data = json.decodeFromString<LatestUpdateResponse>(response.body.string())
        val manga = data.data.map {
            SManga.create().apply {
                url = "/$itemPath/${it.slug}"
                title = it.name
                thumbnail_url = guessCover(url, null)
            }
        }
        val hasNextPage = response.request.url.queryParameter("p")!!.toInt() < data.totalPages

        return MangasPage(manga, hasNextPage)
    }

    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request {
        if (query.isEmpty()) {
            return super.searchMangaRequest(page, query, filters)
        }

        val url = "$baseUrl/search".toHttpUrl().newBuilder().apply {
            addQueryParameter("q", query)
        }.build()

        return GET(url, headers)
    }

    override fun searchMangaParse(response: Response): MangasPage {
        val searchType = response.request.url.pathSegments.last()

        if (searchType != "search") {
            return super.searchMangaParse(response)
        }

        searchDirectory = json.decodeFromString<List<SuggestionDto>>(response.body.string())

        return parseSearchDirectory(1)
    }

    override fun mangaDetailsParse(document: Document) = super.mangaDetailsParse(document).apply {
        status = when (document.selectFirst("div.manga-name span.label")?.text()?.lowercase()) {
            in detailStatusComplete -> SManga.COMPLETED
            in detailStatusOngoing -> SManga.ONGOING
            in detailStatusDropped -> SManga.CANCELLED
            else -> SManga.UNKNOWN
        }
    }

    private var key = ""

    private fun getKey(): String {
        val script = client.newCall(GET("$baseUrl/js/ads2.js")).execute().body.string()
        val deobfuscatedScript = Deobfuscator.deobfuscateScript(script)
            ?: throw Exception("No se pudo desofuscar el script")

        val variable = KEY_REGEX.find(deobfuscatedScript)?.groupValues?.get(1)
            ?: throw Exception("No se pudo encontrar la clave")

        if (variable.startsWith("'")) return variable.removeSurrounding("'")
        if (variable.startsWith("\"")) return variable.removeSurrounding("\"")

        val varRegex = """(?:let|var|const)\s+$variable\s*=\s*['|"](.*)['|"]""".toRegex()
        return varRegex.find(deobfuscatedScript)?.groupValues?.get(1)
            ?: throw Exception("No se pudo encontrar la clave")
    }

    override fun chapterListParse(response: Response): List<SChapter> {
        val document = response.asJsoup()
        val mangaUrl = document.location().removeSuffix("/")
        val encodeChapterData = CHAPTER_DATA_REGEX.find(document.html())?.value ?: throw Exception("No se pudo encontrar la lista de capítulos")
        val unescapedChapterData = encodeChapterData.unescape()
        val chapterData = json.decodeFromString<CDT>(unescapedChapterData)
        val salt = chapterData.s.decodeHex()

        val unsaltedCipherText = Base64.decode(chapterData.ct, Base64.DEFAULT)
        val cipherText = SALTED + salt + unsaltedCipherText

        val decrypted = CryptoAES.decrypt(Base64.encodeToString(cipherText, Base64.DEFAULT), key).ifEmpty {
            key = getKey()
            CryptoAES.decrypt(Base64.encodeToString(cipherText, Base64.DEFAULT), key)
        }.ifEmpty { throw Exception("No se pudo desencriptar los capítulos") }

        val unescaped = decrypted.unescapeJava().removeSurrounding("\"").unescape()

        val chapters = json.decodeFromString<List<Chapter>>(unescaped)

        return chapters.map {
            SChapter.create().apply {
                name = if (it.name == "Capítulo ${it.number}") {
                    it.name
                } else {
                    "Capítulo ${it.number}: ${it.name}"
                }

                date_upload = try {
                    dateFormat.parse(it.createdAt)!!.time
                } catch (_: ParseException) {
                    0L
                }

                setUrlWithoutDomain("$mangaUrl/${it.slug}")
            }
        }
    }

    override fun pageListParse(document: Document): List<Page> = document.select("#all > img.img-responsive").mapIndexed { i, it ->
        var url = it.imgAttr()
        if (url.toHttpUrlOrNull() == null) {
            val decodedB64 = String(Base64.decode(url.substringAfter("://"), Base64.DEFAULT))
            url = URLDecoder.decode(decodedB64, "UTF-8")
        }
        Page(i, imageUrl = url)
    }

    private fun String.unescape(): String = UNESCAPE_REGEX.replace(this, "$1")

    private fun String.unescapeJava(): String {
        var escaped = this
        if (!escaped.contains("\\u")) return escaped
        val builder = StringBuilder()
        var position = escaped.indexOf("\\u")
        while (position != -1) {
            if (position != 0) {
                builder.append(escaped, 0, position)
            }
            val token = escaped.substring(position + 2, position + 6)
            escaped = escaped.substring(position + 6)
            builder.append(Integer.parseInt(token, 16).toChar())
            position = escaped.indexOf("\\u")
        }
        builder.append(escaped)
        return builder.toString()
    }

    private fun String.decodeHex(): ByteArray {
        check(length % 2 == 0) { "Must have an even length" }

        return chunked(2)
            .map { it.toInt(16).toByte() }
            .toByteArray()
    }
}

private val UNESCAPE_REGEX = """\\(.)""".toRegex()
private val CHAPTER_DATA_REGEX = """\{(?=.*\\"ct\\")(?=.*\\"iv\\")(?=.*\\"s\\").*?\}""".toRegex()
private val KEY_REGEX = """decrypt\(.*?,\s*(.*?)\s*,.*\)""".toRegex()
private val SALTED = "Salted__".toByteArray(Charsets.UTF_8)
