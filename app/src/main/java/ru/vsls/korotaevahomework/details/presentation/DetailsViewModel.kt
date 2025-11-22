package ru.vsls.korotaevahomework.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vsls.korotaevahomework.details.domain.usecase.GetLoanUseCase
import ru.vsls.korotaevahomework.details.presentation.model.DetailsState
import ru.vsls.navigation.Router

class DetailsViewModel @AssistedInject constructor(
    @Assisted val loanId: Int?,
    private val getLoanUseCase: GetLoanUseCase,
    private val router: Router,
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(loanId: Int?): DetailsViewModel
    }

    private var _state = MutableStateFlow<DetailsState>(DetailsState.Initial)
    val state = _state.asStateFlow()

    private val _errors = MutableSharedFlow<String>()
    val errors = _errors.asSharedFlow()

    fun loadDetails() {
        _state.value = DetailsState.Loading
        viewModelScope.launch {
            try {
                if (loanId == null) throw Exception("Loan ID is null")

                val loan = getLoanUseCase(loanId)
                _state.update { DetailsState.Content(loan) }
            } catch (ex: Exception) {
                _errors.emit("Error loading loan details: ${ex.message}")
            }
        }
    }

    fun onBackPressed() {
        router.navigateBack()
    }
}