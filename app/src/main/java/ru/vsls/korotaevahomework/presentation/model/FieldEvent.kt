package ru.vsls.korotaevahomework.presentation.model

sealed interface FieldEvent {
    data class LoginChanged(val login: String) : FieldEvent
    data class PasswordChanged(val password: String) : FieldEvent
    data class RepeatPasswordChanged(val repeatPassword: String) : FieldEvent
}