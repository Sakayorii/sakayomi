package dev.sakayori.sakayomi.extension.ja.alphapolis

import dev.sakayori.sakayomi.source.model.SChapter
import Sakayorii.utils.tryParse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import okhttp3.HttpUrl.Companion.toHttpUrl
import java.text.SimpleDateFormat
import java.util.Locale

@Serializable
class ChapterResponse(
    val episodes: List<Episode>,
)

@Serializable
class Episode(
    private val url: String,
    private val mainTitle: String,
    private val upTime: String?,
    private val rental: Rental?,
) {
    val isLocked: Boolean
        get() = rental?.isFree == false && rental.isOnRental != true

    fun toSChapter(baseUrl: String) = SChapter.create().apply {
        val mangaUrl = (baseUrl + this@Episode.url).toHttpUrl()
        val mangaId = mangaUrl.pathSegments[2]
        val episodeId = mangaUrl.pathSegments[3]
        val lock = if (isLocked) "🔒 " else ""
        url = "$mangaId#$episodeId"
        name = lock + mainTitle
        date_upload = dateFormat.tryParse(upTime)
    }
}

private val dateFormat = SimpleDateFormat("yyyy.MM.dd'更新'", Locale.ROOT)

@Serializable
class Rental(
    val isFree: Boolean?,
    val isOnRental: Boolean?,
)

@Suppress("unused")
@Serializable
class ChapterRequestBody(
    @SerialName("manga_id") val mangaId: Int,
)

@Serializable
class ViewerResponse(
    val page: ViewerPage?,
)

@Serializable
class ViewerPage(
    val images: List<ViewerImage>,
)

@Serializable
class ViewerImage(
    val url: String,
)

@Suppress("unused")
@Serializable
class ViewerRequestBody(
    @SerialName("episode_no") val episodeNo: Int,
    @SerialName("hide_page") val hidePage: Boolean,
    @SerialName("manga_sele_id") val mangaSeleId: Int,
    val preview: Boolean,
    val resolution: String,
)
