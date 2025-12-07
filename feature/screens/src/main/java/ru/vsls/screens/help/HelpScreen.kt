package ru.vsls.screens.help

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.vsls.screens.R
import ru.vsls.screens.components.InfoContent
import ru.vsls.ui.components.topbars.CloseTopAppBar

@Composable
fun HelpScreen(
    onBack: () -> Unit
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CloseTopAppBar(navigateTo = onBack)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {

            InfoContent(
                title = stringResource(R.string.help_title),
                description = stringResource(R.string.help_description),
                painter = painterResource(R.drawable.s_question),
            )

        }
    }
}