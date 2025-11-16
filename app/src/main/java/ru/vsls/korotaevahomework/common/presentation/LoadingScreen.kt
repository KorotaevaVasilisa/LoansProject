package ru.vsls.korotaevahomework.common.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.vsls.korotaevahomework.enter.presentation.components.LogoBlock

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