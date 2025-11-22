package ru.vsls.ui.theme

import androidx.compose.ui.graphics.Color

data class StatusColors(
    val error: Color,
    val positive: Color,
    val attention: Color
)

val LightStatusColors = StatusColors(
    error = IndicatorError,
    positive = IndicatorPositive,
    attention = IndicatorAttention
)

val DarkStatusColors = StatusColors(
    error = IndicatorNightError,
    positive = IndicatorPositive,
    attention = IndicatorAttention
)