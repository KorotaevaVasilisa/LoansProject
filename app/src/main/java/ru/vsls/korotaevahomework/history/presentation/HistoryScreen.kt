package ru.vsls.korotaevahomework.history.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.common.LoanItem
import ru.vsls.korotaevahomework.form.domain.model.LoanResponse
import ru.vsls.korotaevahomework.history.presentation.model.HistoryState
import ru.vsls.ui.LoadingScreen
import ru.vsls.ui.components.topbars.BackTopAppBar

@Composable
internal fun HistoryScreen(viewModel: HistoryViewModel) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadHistory()
    }

    val currentState = state
    when (currentState) {
        is HistoryState.Content -> HistoryContent(
            loans = currentState.loans,
            navigateToDetail = viewModel::navigateToDetails,
            navigateToBack = viewModel::navigateToBack
        )

        HistoryState.Initial,
        HistoryState.Loading,
            -> LoadingScreen()
    }
}

@Composable
fun HistoryContent(
    loans: List<LoanResponse>,
    navigateToDetail: (id: Int) -> Unit,
    navigateToBack: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BackTopAppBar(
                title = stringResource(R.string.title_history),
                navigateTo = navigateToBack
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(items = loans, key = { it.id }) { item ->
                LoanItem(
                    loan = item,
                    modifier = Modifier.clickable { navigateToDetail(item.id) })
            }
        }
    }
}