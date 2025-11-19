package ru.vsls.korotaevahomework.main.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.vsls.korotaevahomework.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LoanSlider(
    maxAmount:Int,
    sliderValue: Float,
    onValueChange: (Float) -> Unit,
) {
    val colors = SliderDefaults.colors(
        thumbColor = MaterialTheme.colorScheme.inverseOnSurface,
        activeTrackColor = MaterialTheme.colorScheme.inverseOnSurface,
    )
    Slider(
        modifier = Modifier.fillMaxWidth(),
        value = sliderValue,
        valueRange = 0f..maxAmount.toFloat(),
        onValueChange = onValueChange,
        colors = colors,
        thumb = {
            Thumb()
        },
        track = { sliderState ->
            val width = sliderState.coercedValueAsFraction

            CustomTrack(
                color = MaterialTheme.colorScheme.secondary,
                fraction = 1f
            )
            CustomTrack(color = MaterialTheme.colorScheme.inverseOnSurface,
                fraction = width)

        }
    )
}

@Composable
private fun CustomTrack(color: Color, fraction: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth(fraction)
            .height(4.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(10.dp)
            )
    )
}

@Composable
private fun Thumb() {
    Box(
        modifier = Modifier
            .size(24.dp)
            .background(
                MaterialTheme.colorScheme.inverseOnSurface,
                CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.outline_visibility),
            contentDescription = stringResource(R.string.thumb),
            modifier = Modifier.size(ButtonDefaults.IconSize),
            tint = MaterialTheme.colorScheme.background
        )
    }
}