package ru.vsls.main.presentation.model

import ru.vsls.shared.network.domain.model.LoanResponse
import ru.vsls.main.domain.model.Condition

sealed interface MainState {
    data object Initial : MainState
    data object Loading : MainState

    data class Content(
        val condition: Condition,
        val valueLoan: Int = 7000,
        val valueSlider: Float = 7000f,
        val userLoans: List<LoanResponse> = emptyList(),
    ) : MainState
}

