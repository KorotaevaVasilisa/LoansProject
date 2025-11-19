package ru.vsls.korotaevahomework.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vsls.korotaevahomework.main.domain.usecases.GetConditionsUseCase
import ru.vsls.korotaevahomework.main.presentation.model.MainState
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getConditionsUseCase: GetConditionsUseCase,
) : ViewModel() {
    private var _state = MutableStateFlow<MainState>(MainState.Loading)
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            val condition = getConditionsUseCase()
            _state.update { MainState.Content(condition = condition, valueSlider = 0f) }
        }
    }

    fun onSliderValueChange(value: Float) {
        val state = _state.value as? MainState.Content ?: return

        _state.value = state.copy(valueSlider = value)
    }
}