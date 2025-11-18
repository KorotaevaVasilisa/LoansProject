package ru.vsls.korotaevahomework.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = PermanentPrimary,
    onPrimary = FontDayPrimary,

    secondary = BGDaySecondary,
    onSecondary = FontDayPrimary,

    background = BGDayPrimary,
    inverseOnSurface = BGDayInvert,

    primaryContainer = FontDayPrimary,
    surface = BGDayPrimary,

    onSurfaceVariant = FontDaySecondary,

    error = IndicatorError,

    outline = BGDayTertiary
)

private val DarkColorScheme = darkColorScheme(
    background = BGNightPrimary,
    inverseOnSurface = BGNightInvert,
    surface = BGNightPrimary,
    surfaceVariant = BGNightTertiary,
    onSurfaceVariant = FontNightSecondary,

    secondary = BGNightSecondary,

    primary = PermanentPrimary,
    primaryContainer = FontNightPrimary,
    onPrimary = FontNightPrimary,

    error = IndicatorNightError,
)

@Composable
fun ShiftTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (!darkTheme) LightColorScheme else DarkColorScheme
    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        shapes = Shapes(),
        content = content
    )
}