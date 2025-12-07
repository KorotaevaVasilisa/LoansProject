package ru.vsls.screens.banks

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.vsls.screens.R
import ru.vsls.ui.components.BaseButton
import ru.vsls.ui.components.TitleText
import ru.vsls.ui.components.topbars.CloseTopAppBar

@Composable
fun BanksScreen(
    onBack: () -> Unit,
    navigateTo: () -> Unit,
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CloseTopAppBar(navigateTo = onBack)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
                .windowInsetsPadding(
                    WindowInsets.navigationBars.only(WindowInsetsSides.Bottom)
                ),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            BanksContent(modifier = Modifier.weight(1f, fill = true))
            BaseButton(
                onClick = navigateTo,
                text = stringResource(R.string.back_to_main),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
private fun BanksContent(modifier: Modifier) {
    Box(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.sorry),
                contentDescription = stringResource(R.string.status),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )

            TitleText(text = stringResource(R.string.banks_title))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BanksScreenPreview() {
    BanksScreen({}, {})
}