package ru.vsls.korotaevahomework.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.presentation.EnterState

@Composable
internal fun EnterContent(
    state: EnterState,
    switchToLogin: () -> Unit,
    switchToRegistration: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoBlock(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true)
        )

        CardBlock(
            state = state,
            switchToLogin = switchToLogin,
            switchToRegistration = switchToRegistration
        )
    }
}

@Composable
private fun LogoBlock(modifier: Modifier) {
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