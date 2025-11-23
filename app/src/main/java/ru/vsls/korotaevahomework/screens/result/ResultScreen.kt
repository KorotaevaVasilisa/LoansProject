package ru.vsls.korotaevahomework.screens.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.vsls.korotaevahomework.R
import ru.vsls.ui.components.BaseButton
import ru.vsls.ui.components.DescriptionText
import ru.vsls.ui.components.LoadingBlock
import ru.vsls.ui.components.TitleText
import ru.vsls.ui.components.topbars.CloseTopAppBar

@Composable
fun ResultScreen(
    success: Boolean?,
    amount: Int?,
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
                .padding(paddingValues)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            when (success) {
                true -> {
                    InfoContent(
                        title = stringResource(
                            R.string.success_title,
                            amount ?: stringResource(R.string.money)
                        ),
                        description = stringResource(R.string.success_description),
                        titleButton = stringResource(R.string.show_adress),
                        painter = painterResource(R.drawable.s_success),
                        navigateTo = navigateTo,
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                }

                false -> {
                    InfoContent(
                        title = stringResource(R.string.failed_title),
                        description = stringResource(R.string.failed_description),
                        titleButton = stringResource(R.string.back_to_main),
                        painter = painterResource(R.drawable.s_some_error),
                        navigateTo = onBack,
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                }

                null -> LoadingBlock()
            }
        }
    }
}

@Composable
internal fun InfoContent(
    title: String,
    description: String,
    titleButton: String,
    painter: Painter,
    navigateTo: () -> Unit,
    modifier: Modifier = Modifier,
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

    BaseButton(onClick = navigateTo, text = titleButton, modifier = modifier)
}


@Preview
@Composable
fun ResultScreenPreview() {
    ResultScreen(
        success = false,
        amount = 1000,
        onBack = { },
        navigateTo = { }
    )
}
