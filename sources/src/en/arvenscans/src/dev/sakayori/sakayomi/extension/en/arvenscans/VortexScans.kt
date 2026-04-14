package dev.sakayori.sakayomi.extension.en.arvenscans

import dev.sakayori.sakayomi.multisrc.iken.Iken
import dev.sakayori.sakayomi.network.asObservable
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.source.model.SManga
import rx.Observable

class VortexScans :
    Iken(
        "Vortex Scans",
        "en",
        "https://vortexscans.org",
        "https://api.vortexscans.org",
    ) {

    override fun fetchChapterList(manga: SManga): Observable<List<SChapter>> = client.newCall(chapterListRequest(manga))
        .asObservable()
        .map(::chapterListParse)
}
