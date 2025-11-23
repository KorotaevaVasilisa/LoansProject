package ru.vsls.history.presentation.model

import ru.vsls.shared.network.domain.model.LoanResponse

sealed interface HistoryState {
    data object Initial : HistoryState
    data class Content(val loans: List<LoanResponse>) : HistoryState
    data object Loading : HistoryState
}