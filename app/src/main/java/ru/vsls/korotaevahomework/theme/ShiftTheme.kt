package ru.vsls.korotaevahomework.theme

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

    secondary = FontDaySecondary,
    onSecondary = FontDayPrimary,

    background = BGDayPrimary,

    primaryContainer = FontDayPrimary,
    surface = BGDayPrimary,
    onSurface = FontDayPrimary,

    error = IndicatorError,

    outline = BGDayTertiary
)

private val DarkColorScheme = darkColorScheme(
    background = BGNightPrimary,
    surface = BGNightPrimary,
    surfaceVariant = BGNightTertiary,

    secondary = FontNightSecondary,

    primary = PermanentPrimary,
    primaryContainer = FontNightPrimary,

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