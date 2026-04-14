package dev.sakayori.sakayomi.extension.id.ngamenkomik

import dev.sakayori.sakayomi.multisrc.zeistmanga.Genre
import dev.sakayori.sakayomi.multisrc.zeistmanga.Status
import dev.sakayori.sakayomi.multisrc.zeistmanga.Type
import dev.sakayori.sakayomi.multisrc.zeistmanga.ZeistManga
import dev.sakayori.sakayomi.network.interceptor.rateLimit

class NgamenKomik : ZeistManga("NgamenKomik", "https://ngamenkomik05.blogspot.com", "id") {
    override val client = super.client.newBuilder()
        .rateLimit(3)
        .build()

    // ============================== Filters ===============================
    override val hasFilters = true

    override val hasLanguageFilter = false

    override fun getTypeList() = listOf(
        Type("Semua", ""),
        Type("Manhua", "Manhua"),
        Type("Manhwa", "Manhwa"),
    )

    override fun getStatusList() = listOf(
        Status("Semua", ""),
        Status("Ongoing", "Ongoing"),
        Status("Completed", "Completed"),
    )

    override fun getGenreList() = listOf(
        Genre("Action", "Action"),
        Genre("Adventure", "Adventure"),
        Genre("Comedy", "Comedy"),
        Genre("Drama", "Drama"),
        Genre("Ecchi", "Ecchi"),
        Genre("Fantasy", "Fantasy"),
        Genre("Harem", "Harem"),
        Genre("Horror", "Horror"),
        Genre("Isekai", "Isekai"),
        Genre("Magic", "Magic"),
        Genre("Martial Arts", "Martial Arts"),
        Genre("Mystery", "Mystery"),
        Genre("Reincarnation", "Reincarnation"),
        Genre("Romance", "Romance"),
        Genre("School Life", "School Life"),
        Genre("Shounen", "Shounen"),
        Genre("Slice of Life", "Slice of Life"),
        Genre("Supernatural", "Supernatural"),
        Genre("Thriller", "Thriller"),
    )
}
