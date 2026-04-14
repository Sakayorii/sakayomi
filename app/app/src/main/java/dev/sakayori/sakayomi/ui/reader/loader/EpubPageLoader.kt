package dev.sakayori.sakayomi.ui.reader.loader

import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.ui.reader.model.ReaderPage
import sakayomi.core.archive.EpubReader

/**
 * Loader used to load a chapter from a .epub file.
 */
internal class EpubPageLoader(private val reader: EpubReader) : PageLoader() {

    override var isLocal: Boolean = true

    override suspend fun getPages(): List<ReaderPage> {
        return reader.getImagesFromPages().mapIndexed { i, path ->
            ReaderPage(i).apply {
                stream = { reader.getInputStream(path)!! }
                status = Page.State.Ready
            }
        }
    }

    override suspend fun loadPage(page: ReaderPage) {
        check(!isRecycled)
    }

    override fun recycle() {
        super.recycle()
        reader.close()
    }
}
