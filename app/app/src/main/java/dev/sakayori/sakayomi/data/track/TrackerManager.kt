package dev.sakayori.sakayomi.data.track

import dev.sakayori.sakayomi.data.track.anilist.Anilist
import dev.sakayori.sakayomi.data.track.bangumi.Bangumi
import dev.sakayori.sakayomi.data.track.kavita.Kavita
import dev.sakayori.sakayomi.data.track.kitsu.Kitsu
import dev.sakayori.sakayomi.data.track.komga.Komga
import dev.sakayori.sakayomi.data.track.mangaupdates.MangaUpdates
import dev.sakayori.sakayomi.data.track.myanimelist.MyAnimeList
import dev.sakayori.sakayomi.data.track.shikimori.Shikimori
import dev.sakayori.sakayomi.data.track.suwayomi.Suwayomi
import kotlinx.coroutines.flow.combine

class TrackerManager {

    companion object {
        const val ANILIST = 2L
        const val KITSU = 3L
        const val KAVITA = 8L
    }

    val myAnimeList = MyAnimeList(1L)
    val aniList = Anilist(ANILIST)
    val kitsu = Kitsu(KITSU)
    val shikimori = Shikimori(4L)
    val bangumi = Bangumi(5L)
    val komga = Komga(6L)
    val mangaUpdates = MangaUpdates(7L)
    val kavita = Kavita(KAVITA)
    val suwayomi = Suwayomi(9L)

    val trackers = listOf(myAnimeList, aniList, kitsu, shikimori, bangumi, komga, mangaUpdates, kavita, suwayomi)

    fun loggedInTrackers() = trackers.filter { it.isLoggedIn }

    fun loggedInTrackersFlow() = combine(trackers.map { it.isLoggedInFlow }) {
        it.mapIndexedNotNull { index, isLoggedIn ->
            if (isLoggedIn) trackers[index] else null
        }
    }

    fun get(id: Long) = trackers.find { it.id == id }

    fun getAll(ids: Set<Long>) = trackers.filter { it.id in ids }
}
