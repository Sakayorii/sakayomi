package dev.sakayori.sakayomi.extension.ja.rawdevartart

import dev.sakayori.sakayomi.extension.ja.rawdevartart.dto.ChapterDto
import dev.sakayori.sakayomi.extension.ja.rawdevartart.dto.ChapterResponseDto
import dev.sakayori.sakayomi.extension.ja.rawdevartart.dto.MangaDetailDto
import dev.sakayori.sakayomi.extension.ja.rawdevartart.dto.MangaListResponseDto
import dev.sakayori.sakayomi.extension.ja.rawdevartart.dto.MangaResponseDto
import dev.sakayori.sakayomi.source.model.MangasPage
import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.source.model.SManga
import org.jsoup.Jsoup
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.Locale

private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)

fun MangaListResponseDto.toMangasPage(): MangasPage {
    val manga = mangaList.map { it.toSManga() }
    val hasNextPage = (pagi.button?.next ?: 0) != 0

    return MangasPage(manga, hasNextPage)
}

private fun MangaDetailDto.toSManga() = SManga.create().apply {
    // The website URL is manually calculated using a slugify function that I am too
    // lazy to reimplement.
    url = "/spa/manga/$id"
    title = name
    thumbnail_url = coverImage
}

fun MangaResponseDto.toSManga() = SManga.create().apply {
    title = detail.name
    author = authors.joinToString { it.name }
    description = buildString {
        if (!detail.alternativeName.isNullOrEmpty()) {
            append("Alternative Title: ")
            appendLine(detail.alternativeName)
            appendLine()
        }

        if (!detail.description.isNullOrEmpty()) {
            append(detail.description)
        }
    }
    genre = tags.joinToString { it.name }
    status = if (detail.status) SManga.COMPLETED else SManga.ONGOING
    thumbnail_url = detail.coverImageFull ?: detail.coverImage
}

fun MangaResponseDto.toSChapterList() = chapters.map { it.toSChapter(detail.id) }

private fun ChapterDto.toSChapter(mangaId: Int) = SChapter.create().apply {
    url = "/spa/manga/$mangaId/$number"
    name = buildString {
        append("Chapter ")
        append(formatChapterNumber(number))

        if (title.isNotEmpty()) {
            append(": ")
            append(title)
        }
    }
    chapter_number = number
    date_upload = runCatching {
        dateFormat.parse(datePublished)!!.time
    }.getOrDefault(0L)
}

fun ChapterResponseDto.toPageList(): List<Page> {
    val document = Jsoup.parseBodyFragment(detail.content!!, detail.server)

    return document.select("img[data-src]").mapIndexed { i, it ->
        Page(i, imageUrl = it.absUrl("data-src"))
    }
}

private val formatter = DecimalFormat(
    "#.###",
    DecimalFormatSymbols().apply { decimalSeparator = '.' },
)

fun formatChapterNumber(chapterNumber: Float): String = formatter.format(chapterNumber)
