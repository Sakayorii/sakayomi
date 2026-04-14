@file:Suppress("ktlint:standard:package-name")

package dev.sakayori.sakayomi.extension.id.Luvyaa

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class Luvyaa :
    MangaThemesia(
        "Luvyaa",
        "https://v1.luvyaa.co",
        "id",
        dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.US),
    )
