package ru.vsls.korotaevahomework.details.presentation.model

import ru.vsls.shared.network.domain.LoanResponse

sealed interface DetailsState {
    data object Initial : DetailsState
    data object Loading : DetailsState
    data class Content(val loan: LoanResponse) : DetailsState
}