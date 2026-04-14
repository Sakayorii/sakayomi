package dev.sakayori.sakayomi.ui.reader.model

import dev.sakayori.sakayomi.source.model.Page
import java.io.InputStream

open class ReaderPage(
    index: Int,
    url: String = "",
    imageUrl: String? = null,
    var stream: (() -> InputStream)? = null,
) : Page(index, url, imageUrl, null) {

    open lateinit var chapter: ReaderChapter
}
