package ru.vsls.history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.vsls.history.domain.usecase.GetHistoryUseCase
import ru.vsls.history.presentation.model.HistoryState
import ru.vsls.navigation.Router
import ru.vsls.navigation.Screen
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase,
    private val router: Router,
) :
    ViewModel() {
    private var _state = MutableStateFlow<HistoryState>(HistoryState.Initial)
    val state = _state.asStateFlow()

    fun loadHistory() {
        if (_state.value is HistoryState.Content) return

        _state.value = HistoryState.Loading
        viewModelScope.launch {
            val loans = getHistoryUseCase()
            _state.value = HistoryState.Content(loans)
        }

    }

    fun navigateToDetails(id: Int) {
        router.navigateTo(Screen.DetailsScreen(id))
    }

    fun navigateToBack() {
        router.navigateBack()
    }
}