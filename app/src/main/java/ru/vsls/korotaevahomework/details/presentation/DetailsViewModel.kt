package ru.vsls.korotaevahomework.details.presentation

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.vsls.korotaevahomework.details.domain.usecase.GetLoanUseCase
import ru.vsls.korotaevahomework.details.presentation.model.DetailsState
import ru.vsls.navigation.Router

class DetailsViewModel @AssistedInject constructor(
    @Assisted private val loanId: Int?,
    private val getLoanUseCase: GetLoanUseCase,
    private val router: Router
) : ViewModel() {

    @AssistedFactory
    interface Factory{
        fun create(loanId: Int?): DetailsViewModel
    }
    private var _state = MutableStateFlow<DetailsState>(DetailsState.Initial)
    val state = _state.asStateFlow()

    fun loadDetails() {
        _state.value = DetailsState.Loading

    }

}