package ru.vsls.enter.presentation.model

sealed interface EnterState {
    data object Initial : EnterState

    data object Loading : EnterState

    data class Login(
        val login: String = "",
        val password: String = "",
        val isLoginError: Boolean = false,
    ) : EnterState

    data class Registration(
        val login: String = "",
        val isLoginError: Boolean = false,
        val password: String = "",
        val passwordRepeat: String = "",
        val isPasswordError: Boolean = false,
    ) : EnterState
}