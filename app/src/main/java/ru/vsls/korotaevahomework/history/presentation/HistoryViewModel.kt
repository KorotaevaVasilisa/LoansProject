package ru.vsls.korotaevahomework.history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.vsls.korotaevahomework.history.presentation.model.HistoryState

class HistoryViewModel: ViewModel() {
    private var _state = MutableStateFlow<HistoryState>(HistoryState.Initial)
    val state = _state.asStateFlow()

    fun loadHistory() {
        _state.value = HistoryState.Loading

        _state.value = HistoryState.Content(emptyList())

    }
}