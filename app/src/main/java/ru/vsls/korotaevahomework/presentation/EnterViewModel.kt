package ru.vsls.korotaevahomework.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EnterViewModel : ViewModel() {
    private var _state = MutableStateFlow<EnterState>(EnterState.Initial)
    val state = _state.asStateFlow()

    fun initForm() {
        _state.update { EnterState.Login() }
    }

    fun switchToLogin() {
        _state.update { EnterState.Login() }
    }

    fun switchToRegistration() {
        _state.update { EnterState.Registration() }
    }
}