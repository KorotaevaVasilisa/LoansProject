package ru.vsls.korotaevahomework.history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.vsls.korotaevahomework.history.domain.usecase.GetHistoryUseCase
import ru.vsls.korotaevahomework.history.presentation.model.HistoryState
import javax.inject.Inject

class HistoryViewModel @Inject constructor(private val getHistoryUseCase: GetHistoryUseCase) :
    ViewModel() {
    private var _state = MutableStateFlow<HistoryState>(HistoryState.Initial)
    val state = _state.asStateFlow()

    fun loadHistory() {
        _state.value = HistoryState.Loading
        viewModelScope.launch {
            val loans = getHistoryUseCase()
            _state.value = HistoryState.Content(loans)
        }

    }

    fun navigateToDetails(id: Int) {}
}