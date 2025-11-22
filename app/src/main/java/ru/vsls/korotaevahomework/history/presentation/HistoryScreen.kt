package ru.vsls.korotaevahomework.history.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import ru.vsls.enter.common.LoadingScreen
import ru.vsls.korotaevahomework.common.ui.LoanItem
import ru.vsls.korotaevahomework.form.domain.model.LoanResponse
import ru.vsls.korotaevahomework.history.presentation.model.HistoryState

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
            navigateToDetail = viewModel::navigateToDetails
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
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = loans, key = { it.id }) { item ->
            LoanItem(
                loan = item,
                modifier = Modifier.clickable { navigateToDetail(item.id) })
        }
    }
}