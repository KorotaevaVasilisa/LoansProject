package ru.vsls.korotaevahomework.screens.offers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.screens.components.InfoContent
import ru.vsls.ui.components.BaseButton
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
                .padding(16.dp)
        ) {

            InfoContent(
                title = stringResource(R.string.offers_title),
                description = stringResource(R.string.offers_description),
                painter = painterResource(R.drawable.s_approve_money),
                modifier = Modifier.weight(1f, fill = true)
            )

            BaseButton(
                onClick = navigateTo,
                text = stringResource(R.string.show_address),
            )

        }
    }
}