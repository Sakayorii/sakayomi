package dev.sakayori.sakayomi.extension.id.manhwaindo

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import okhttp3.Response
import java.text.SimpleDateFormat
import java.util.Locale

class ManhwaIndo :
    MangaThemesia(
        "Manhwa Indo",
        "https://www.manhwaindo.my",
        "id",
        "/series",
        SimpleDateFormat("MMMM dd, yyyy", Locale.US),
    ) {
    override val hasProjectPage = true

    override fun pageListParse(response: Response) = super.pageListParse(response).distinctBy {
        it.imageUrl!!
    }
}
