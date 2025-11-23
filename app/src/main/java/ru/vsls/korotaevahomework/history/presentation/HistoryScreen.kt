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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.common.utils.formatDateShort
import ru.vsls.shared.network.domain.LoanResponse
import ru.vsls.shared.network.domain.LoanState
import ru.vsls.korotaevahomework.history.presentation.model.HistoryState
import ru.vsls.ui.components.LoadingBlock
import ru.vsls.ui.components.LoanItem
import ru.vsls.ui.components.topbars.BackTopAppBar
import ru.vsls.ui.theme.LocalStatusColors

@Composable
internal fun HistoryScreen(viewModel: HistoryViewModel) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadHistory()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BackTopAppBar(
                title = stringResource(R.string.title_history),
                navigateTo = viewModel::navigateToBack
            )
        }
    ) { paddingValues ->
        val currentState = state
        when (currentState) {
            is HistoryState.Content -> HistoryContent(
                loans = currentState.loans,
                navigateToDetail = viewModel::navigateToDetails,
                modifier = Modifier.padding(paddingValues)
            )

            HistoryState.Initial,
            HistoryState.Loading,
                -> LoadingBlock(modifier = Modifier.padding(paddingValues))
        }
    }
}

@Composable
fun HistoryContent(
    loans: List<LoanResponse>,
    navigateToDetail: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(items = loans, key = { it.id }) { item ->
            LoanItem(
                id = item.id,
                amount = item.amount,
                date = item.date,
                stateText = getStateText(item.state),
                stateColor = getStateColor(item.state),
                modifier = Modifier.clickable { navigateToDetail(item.id) })
        }
    }

}

@Composable
private fun getStateText(state: LoanState): String {
    return when (state) {
        LoanState.APPROVED -> stringResource(R.string.approved_state)
        LoanState.REGISTERED -> stringResource(R.string.registered_state)
        LoanState.REJECTED -> stringResource(R.string.rejected_state)
    }
}

@Composable
private fun getStateColor(state: LoanState): Color {
    return when (state) {
        LoanState.APPROVED -> LocalStatusColors.current.positive
        LoanState.REGISTERED -> LocalStatusColors.current.attention
        LoanState.REJECTED -> LocalStatusColors.current.error
    }
}