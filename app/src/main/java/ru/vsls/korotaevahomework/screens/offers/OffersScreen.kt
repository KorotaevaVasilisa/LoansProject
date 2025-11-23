package ru.vsls.korotaevahomework.screens.offers

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
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.screens.result.InfoContent
import ru.vsls.ui.components.topbars.CloseTopAppBar

@Composable
fun OffersScreen(
    onBack: () -> Unit,
    navigateTo: () -> Unit,
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
                .padding(top = paddingValues.calculateTopPadding())
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {

            InfoContent(
                title = stringResource(R.string.title_offers),
                description = stringResource(R.string.offers_description),
                titleButton = stringResource(R.string.show_adress),
                painter = painterResource(R.drawable.s_approve_money),
                navigateTo = navigateTo,
                modifier = Modifier.align(Alignment.BottomCenter)
            )

        }
    }
}