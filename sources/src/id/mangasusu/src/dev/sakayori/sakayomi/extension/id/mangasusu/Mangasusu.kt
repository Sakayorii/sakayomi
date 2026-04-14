package dev.sakayori.sakayomi.extension.id.mangasusu

import app.cash.quickjs.QuickJs
import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.util.asJsoup
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import okhttp3.Cookie
import okhttp3.Interceptor
import okhttp3.Response
import org.jsoup.nodes.Document
import java.io.IOException

class Mangasusu :
    MangaThemesia(
        "Mangasusu",
        "https://mangasusuku.com",
        "id",
        "/komik",
    ) {
    override val client by lazy {
        super.client.newBuilder().addInterceptor(::sucuriInterceptor).build()
    }

    // Taken from es/ManhwasNet
    private fun sucuriInterceptor(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url
        val response = try {
            chain.proceed(request)
        } catch (e: Exception) {
            // Try to clear cookies and retry
            client.cookieJar.saveFromResponse(url, emptyList())
            val clearHeaders = request.headers.newBuilder().removeAll("Cookie").build()
            chain.proceed(request.newBuilder().headers(clearHeaders).build())
        }
        if (response.headers["x-sucuri-cache"].isNullOrEmpty() && response.headers["x-sucuri-id"] != null && url.toString().startsWith(baseUrl)) {
            val script = response.use { it.asJsoup() }.selectFirst("script")?.data()

            if (script != null) {
                val patchedScript = script.split("(r)")[0].dropLast(1) + "r=r.replace('document.cookie','cookie');"
                QuickJs.create().use {
                    val result = (it.evaluate(patchedScript) as String)
                        .replace("location.", "")
                        .replace("reload();", "")
                    val sucuriCookie = (it.evaluate(result) as String).split("=", limit = 2)
                    val cookieName = sucuriCookie.first()
                    val cookieValue = sucuriCookie.last().replace(";path", "")
                    client.cookieJar.saveFromResponse(url, listOf(Cookie.parse(url, "$cookieName=$cookieValue")!!))
                }
                val newResponse = chain.proceed(request)
                if (!newResponse.headers["x-sucuri-cache"].isNullOrEmpty()) return newResponse
            }
            throw IOException("Situs yang dilindungi - Buka di WebView untuk mencoba membuka blokir.")
        }
        return response
    }

    override fun pageListParse(document: Document): List<Page> {
        val scriptContent = document.selectFirst("script:containsData(ts_reader)")?.data()
            ?: return super.pageListParse(document)
        val jsonString = scriptContent.substringAfter("ts_reader.run(").substringBefore(");")
        val tsReader = json.decodeFromString<TSReader>(jsonString)
        val imageUrls = tsReader.sources.firstOrNull()?.images ?: return emptyList()
        return imageUrls.mapIndexed { index, imageUrl -> Page(index, document.location(), imageUrl) }
    }

    @Serializable
    data class TSReader(
        val sources: List<ReaderImageSource>,
    )

    @Serializable
    data class ReaderImageSource(
        val source: String,
        val images: List<String>,
    )
}
