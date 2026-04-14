package dev.sakayori.sakayomi.ui.reader.loader

import com.hippo.unifile.UniFile
import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.ui.reader.model.ReaderPage
import dev.sakayori.sakayomi.util.lang.compareToCaseInsensitiveNaturalOrder
import tachiyomi.core.common.util.system.ImageUtil

/**
 * Loader used to load a chapter from a directory given on [file].
 */
internal class DirectoryPageLoader(val file: UniFile) : PageLoader() {

    override var isLocal: Boolean = true

    override suspend fun getPages(): List<ReaderPage> {
        return file.listFiles()
            ?.filter { !it.isDirectory && ImageUtil.isImage(it.name) { it.openInputStream() } }
            ?.sortedWith { f1, f2 -> f1.name.orEmpty().compareToCaseInsensitiveNaturalOrder(f2.name.orEmpty()) }
            ?.mapIndexed { i, file ->
                val streamFn = { file.openInputStream() }
                ReaderPage(i).apply {
                    stream = streamFn
                    status = Page.State.Ready
                }
            }
            .orEmpty()
    }
}
