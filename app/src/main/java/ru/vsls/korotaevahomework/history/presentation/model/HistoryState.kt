package ru.vsls.korotaevahomework.history.presentation.model

import ru.vsls.shared.network.domain.LoanResponse

sealed interface HistoryState {
    data object Initial : HistoryState
    data class Content(val loans: List<LoanResponse>) : HistoryState
    data object Loading : HistoryState
}