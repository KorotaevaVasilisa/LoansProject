package ru.vsls.enter.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.vsls.enter.R

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.Companion
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier.Companion.align(Alignment.Companion.Center),
            horizontalAlignment = Alignment.Companion.CenterHorizontally
        ) {
            LogoBlock()
            CircularProgressIndicator()
        }
    }
}

@Composable
fun LogoBlock(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = getCurrentLogo(),
            contentDescription = stringResource(R.string.logo),
            modifier = Modifier
                .align(Alignment.Center)
                .height(100.dp)
                .width(270.dp)
        )
    }
}

@Composable
private fun getCurrentLogo(): Painter {
    return if (!isSystemInDarkTheme())
        painterResource(id = R.drawable.logo_light) else
        painterResource(id = R.drawable.logo_night)
}