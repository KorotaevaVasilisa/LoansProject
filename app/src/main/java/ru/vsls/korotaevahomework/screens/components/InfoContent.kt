package ru.vsls.korotaevahomework.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.vsls.korotaevahomework.R
import ru.vsls.ui.components.DescriptionText
import ru.vsls.ui.components.TitleText

@Composable
internal fun InfoContent(
    title: String,
    description: String,
    painter: Painter,

) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painter,
            contentDescription = stringResource(R.string.status),
            modifier = Modifier.size(96.dp)
        )

        TitleText(text = title)

        DescriptionText(description)
    }
}