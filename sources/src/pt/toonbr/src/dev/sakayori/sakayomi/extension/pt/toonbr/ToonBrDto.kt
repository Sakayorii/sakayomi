package dev.sakayori.sakayomi.extension.pt.toonbr

import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.source.model.SManga
import Sakayorii.utils.tryParse
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat

@Serializable
internal data class MangaDto(
    val id: String,
    val title: String,
    val slug: String,
    val description: String? = null,
    val status: String? = null,
    val coverImage: String? = null,
    val chapters: List<ChapterDto>? = null,
) {
    fun toSManga(cdnUrl: String) = SManga.create().apply {
        url = "/manga/$slug"
        title = this@MangaDto.title
        thumbnail_url = coverImage?.let { "$cdnUrl$it" }
        description = this@MangaDto.description
        status = when (this@MangaDto.status) {
            "ONGOING" -> SManga.ONGOING
            "COMPLETED" -> SManga.COMPLETED
            else -> SManga.UNKNOWN
        }
    }
}

@Serializable
internal data class ChapterDto(
    val id: String,
    val title: String,
    val chapterNumber: Float? = null,
    val createdAt: String? = null,
    val pages: List<PageDto>? = null,
) {
    fun toSChapter(dateFormat: SimpleDateFormat) = SChapter.create().apply {
        url = "/chapter/$id"
        name = chapterNumber?.let { "Capítulo ${it.formatNumber()}" } ?: this@ChapterDto.title
        chapter_number = this@ChapterDto.chapterNumber ?: 0f
        date_upload = createdAt?.let { dateFormat.tryParse(it) } ?: 0L
    }

    private fun Float.formatNumber(): String = if (this % 1 == 0f) this.toInt().toString() else this.toString()
}

@Serializable
internal data class PageDto(
    val id: String? = null,
    val imageUrl: String? = null,
    val pageNumber: Int? = null,
)

@Serializable
internal data class LoginResponse(
    val token: String,
)

@Serializable
internal data class MangaListResponse(
    val data: List<MangaDto>,
)
