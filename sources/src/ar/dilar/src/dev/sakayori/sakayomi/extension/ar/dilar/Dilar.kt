package dev.sakayori.sakayomi.extension.ar.dilar

import android.content.SharedPreferences
import android.widget.Toast
import androidx.preference.ListPreference
import androidx.preference.PreferenceScreen
import dev.sakayori.sakayomi.multisrc.gmanga.Gmanga
import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.source.ConfigurableSource
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.source.model.SManga
import Sakayorii.utils.getPreferencesLazy
import okhttp3.Request
import okhttp3.Response

private const val MIRROR_PREF_KEY = "MIRROR"
private const val MIRROR_PREF_TITLE = "Dilar : Mirror Urls"
private val MIRROR_PREF_ENTRY_VALUES = arrayOf("https://dilar.tube", "https://golden.rest")
private val MIRROR_PREF_DEFAULT_VALUE = MIRROR_PREF_ENTRY_VALUES[0]
private const val RESTART_TACHIYOMI = ".لتطبيق الإعدادات الجديدة Sakayomi أعد تشغيل"

class Dilar :
    Gmanga(
        "Dilar",
        MIRROR_PREF_DEFAULT_VALUE,
        "ar",
    ),
    ConfigurableSource {
    override fun chaptersRequest(manga: SManga): Request {
        val mangaId = manga.url.substringAfterLast("/")
        return GET("$baseUrl/api/mangas/$mangaId/releases", headers)
    }

    override fun chaptersParse(response: Response): List<SChapter> {
        val releases = response.parseAs<ChapterListDto>().releases
            .filterNot { it.isMonetized }

        return releases.map { it.toSChapter() }
    }

    override fun setupPreferenceScreen(screen: PreferenceScreen) {
        val mirrorPref = ListPreference(screen.context).apply {
            key = MIRROR_PREF_KEY
            title = MIRROR_PREF_TITLE
            entries = MIRROR_PREF_ENTRY_VALUES
            entryValues = MIRROR_PREF_ENTRY_VALUES
            setDefaultValue(MIRROR_PREF_DEFAULT_VALUE)
            summary = "%s"

            setOnPreferenceChangeListener { _, _ ->
                Toast.makeText(screen.context, RESTART_TACHIYOMI, Toast.LENGTH_LONG).show()
                true
            }
        }
        screen.addPreference(mirrorPref)
    }

    private fun mirrorPref() = when {
        System.getenv("CI") == "true" -> MIRROR_PREF_ENTRY_VALUES.joinToString("#, ")
        else -> preferences.getString(MIRROR_PREF_KEY, MIRROR_PREF_DEFAULT_VALUE)!!
    }

    override val baseUrl by lazy { mirrorPref() }

    override val cdnUrl by lazy { baseUrl }

    private val preferences: SharedPreferences by getPreferencesLazy()
}
