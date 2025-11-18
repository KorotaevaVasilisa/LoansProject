package ru.vsls.korotaevahomework.main.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.vsls.korotaevahomework.main.presentation.model.MainState
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {
    private var _state = MutableStateFlow<MainState>(MainState.Content())
    val state = _state.asStateFlow()

    fun getLoanAmount(): String {
        val state = _state.value as? MainState.Content ?: return ""
        val loan = state.valueSlider.toInt().toString() + " ₽"
        return loan
    }

    fun onSliderValueChange(value: Float) {
        println("Slider value changed to $value")
    }
}