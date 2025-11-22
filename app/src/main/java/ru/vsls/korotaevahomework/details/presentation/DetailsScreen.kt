package ru.vsls.korotaevahomework.details.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import ru.vsls.korotaevahomework.details.presentation.model.DetailsState
import ru.vsls.ui.components.LoadingBlock
import ru.vsls.ui.components.topbars.BackTopAppBar

@Composable
internal fun DetailsScreen(viewModel: DetailsViewModel) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BackTopAppBar(
                title = "№ ${viewModel.loanId}",
                navigateTo = viewModel::onBackPressed
            )
        }
    ) { paddingValues ->
        when (state) {
            is DetailsState.Content -> TODO()
            DetailsState.Initial,
            DetailsState.Loading,
                -> LoadingBlock(Modifier.padding(paddingValues))
        }

    }
}