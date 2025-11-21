package ru.vsls.korotaevahomework.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vsls.korotaevahomework.main.domain.usecases.GetConditionsUseCase
import ru.vsls.korotaevahomework.main.presentation.model.MainState
import ru.vsls.navigation.Router
import ru.vsls.navigation.Screen
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getConditionsUseCase: GetConditionsUseCase,
    private val router: Router,
) : ViewModel() {
    private var _state = MutableStateFlow<MainState>(MainState.Loading)
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            try {
                _state.update { MainState.Content(condition = null, valueSlider = 0f) }
                val condition = getConditionsUseCase()
                _state.update { MainState.Content(condition = condition) }
            } catch (ex: Exception) {

            }
        }
    }

    fun onSliderValueChange(value: Float) {
        val state = _state.value as? MainState.Content ?: return

        _state.value = state.copy(valueSlider = value, valueLoan = value.toInt())
    }

    fun navigateToForm() {
        val state = _state.value as? MainState.Content ?: return
        if (state.condition == null) return

        router.navigateTo(
            Screen.FormScreen(
                amount = state.valueLoan,
                period = state.condition.period,
                percent = state.condition.percent
            )
        )
    }
}