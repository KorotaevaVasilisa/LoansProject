package ru.vsls.screens.result

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.vsls.screens.R
import ru.vsls.screens.components.InfoContent
import ru.vsls.ui.components.BaseButton
import ru.vsls.ui.components.LoadingBlock
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
                .padding(16.dp)
        ) {
            when (success) {
                true -> {
                    InfoContent(
                        title = stringResource(
                            R.string.success_title,
                            amount ?: stringResource(R.string.money)
                        ),
                        description = stringResource(R.string.success_description),
                        painter = painterResource(R.drawable.s_success),
                        modifier = Modifier.weight(1f, fill = true)
                    )
                    BaseButton(
                        onClick = navigateTo,
                        text = stringResource(R.string.show_address)
                    )

                }

                false -> {
                    InfoContent(
                        title = stringResource(R.string.failed_title),
                        description = stringResource(R.string.failed_description),
                        painter = painterResource(R.drawable.s_some_error),
                        modifier = Modifier.weight(1f, fill = true)
                    )
                    BaseButton(
                        onClick = onBack,
                        text = stringResource(R.string.back_to_main)
                    )
                }

                null -> LoadingBlock()
            }
        }
    }
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
