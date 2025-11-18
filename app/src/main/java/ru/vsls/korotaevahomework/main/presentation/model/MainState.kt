package ru.vsls.korotaevahomework.main.presentation.model

sealed interface MainState {
    data object Initial : MainState
    data object Loading : MainState

    data class Content(
        val valueSlider: Float = 0f
    ) : MainState
}