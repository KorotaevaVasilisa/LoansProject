package ru.vsls.korotaevahomework.history.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.vsls.enter.common.LoadingScreen
import ru.vsls.korotaevahomework.common.ui.LoanItem
import ru.vsls.korotaevahomework.form.domain.model.LoanResponse
import ru.vsls.korotaevahomework.history.presentation.model.HistoryState

@Composable
internal fun HistoryScreen(viewModel: HistoryViewModel) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is HistoryState.Content -> HistoryContent(emptyList())

        HistoryState.Initial,
        HistoryState.Loading,
            -> LoadingScreen()
    }
}

@Composable
fun HistoryContent(loans: List<LoanResponse>) {
    LazyColumn {
        items(loans.size) { index ->
            LoanItem(loan = loans[index])
        }
    }
}