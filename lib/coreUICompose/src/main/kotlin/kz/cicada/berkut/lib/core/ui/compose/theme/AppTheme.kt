package kz.cicada.berkut.lib.core.ui.compose.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

enum class ThemeType {
    DARK_THEME,
    SYSTEM_THEME,
    LIGHT_THEME,
    ;

    companion object {
        val Default = LIGHT_THEME
    }
}

@Composable
fun AppTheme(
    themeType: ThemeType = ThemeType.Default,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalThemeType provides themeType,
        LocalAdditionalColorsProvider provides additionalLightColors,
    ) {
        MaterialTheme(
            colors = colors,
            typography = StrongHubTypography,
            shapes = shapes,
            content = content,
        )
    }
}

val LocalThemeType = staticCompositionLocalOf {
    ThemeType.Default
}

val themeType: ThemeType
    @Composable
    @ReadOnlyComposable
    get() = LocalThemeType.current
