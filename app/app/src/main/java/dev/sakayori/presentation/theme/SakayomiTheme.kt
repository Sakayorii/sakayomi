package dev.sakayori.presentation.theme

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import dev.sakayori.domain.ui.UiPreferences
import dev.sakayori.domain.ui.model.AppTheme
import dev.sakayori.presentation.theme.colorscheme.BaseColorScheme
import dev.sakayori.presentation.theme.colorscheme.CatppuccinColorScheme
import dev.sakayori.presentation.theme.colorscheme.GreenAppleColorScheme
import dev.sakayori.presentation.theme.colorscheme.LavenderColorScheme
import dev.sakayori.presentation.theme.colorscheme.MidnightDuskColorScheme
import dev.sakayori.presentation.theme.colorscheme.MonetColorScheme
import dev.sakayori.presentation.theme.colorscheme.MonochromeColorScheme
import dev.sakayori.presentation.theme.colorscheme.NordColorScheme
import dev.sakayori.presentation.theme.colorscheme.StrawberryColorScheme
import dev.sakayori.presentation.theme.colorscheme.SakayomiColorScheme
import dev.sakayori.presentation.theme.colorscheme.TakoColorScheme
import dev.sakayori.presentation.theme.colorscheme.TealTurqoiseColorScheme
import dev.sakayori.presentation.theme.colorscheme.TidalWaveColorScheme
import dev.sakayori.presentation.theme.colorscheme.YinYangColorScheme
import dev.sakayori.presentation.theme.colorscheme.YotsubaColorScheme
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

@Composable
fun SakayomiTheme(
    appTheme: AppTheme? = null,
    amoled: Boolean? = null,
    content: @Composable () -> Unit,
) {
    val uiPreferences = Injekt.get<UiPreferences>()
    BaseSakayomiTheme(
        appTheme = appTheme ?: uiPreferences.appTheme.get(),
        isAmoled = amoled ?: uiPreferences.themeDarkAmoled.get(),
        content = content,
    )
}

@Composable
fun TachiyomiPreviewTheme(
    appTheme: AppTheme = AppTheme.DEFAULT,
    isAmoled: Boolean = false,
    content: @Composable () -> Unit,
) = BaseSakayomiTheme(appTheme, isAmoled, content)

@Composable
private fun BaseSakayomiTheme(
    appTheme: AppTheme,
    isAmoled: Boolean,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val isDark = isSystemInDarkTheme()
    MaterialExpressiveTheme(
        colorScheme = remember(appTheme, isDark, isAmoled) {
            getThemeColorScheme(
                context = context,
                appTheme = appTheme,
                isDark = isDark,
                isAmoled = isAmoled,
            )
        },
        content = content,
    )
}

private fun getThemeColorScheme(
    context: Context,
    appTheme: AppTheme,
    isDark: Boolean,
    isAmoled: Boolean,
): ColorScheme {
    val colorScheme = if (appTheme == AppTheme.MONET) {
        MonetColorScheme(context)
    } else {
        colorSchemes.getOrDefault(appTheme, SakayomiColorScheme)
    }
    return colorScheme.getColorScheme(
        isDark = isDark,
        isAmoled = isAmoled,
        overrideDarkSurfaceContainers = appTheme != AppTheme.MONET,
    )
}

private val colorSchemes: Map<AppTheme, BaseColorScheme> = mapOf(
    AppTheme.DEFAULT to SakayomiColorScheme,
    AppTheme.CATPPUCCIN to CatppuccinColorScheme,
    AppTheme.GREEN_APPLE to GreenAppleColorScheme,
    AppTheme.LAVENDER to LavenderColorScheme,
    AppTheme.MIDNIGHT_DUSK to MidnightDuskColorScheme,
    AppTheme.MONOCHROME to MonochromeColorScheme,
    AppTheme.NORD to NordColorScheme,
    AppTheme.STRAWBERRY_DAIQUIRI to StrawberryColorScheme,
    AppTheme.TAKO to TakoColorScheme,
    AppTheme.TEALTURQUOISE to TealTurqoiseColorScheme,
    AppTheme.TIDAL_WAVE to TidalWaveColorScheme,
    AppTheme.YINYANG to YinYangColorScheme,
    AppTheme.YOTSUBA to YotsubaColorScheme,
)
