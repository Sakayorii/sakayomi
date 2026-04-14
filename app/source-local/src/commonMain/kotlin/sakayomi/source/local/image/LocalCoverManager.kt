package tachiyomi.source.local.image

import com.hippo.unifile.UniFile
import dev.sakayori.sakayomi.source.model.SManga
import java.io.InputStream

expect class LocalCoverManager {

    fun find(mangaUrl: String): UniFile?

    fun update(manga: SManga, inputStream: InputStream): UniFile?
}
