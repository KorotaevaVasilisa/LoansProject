package ru.vsls.korotaevahomework.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.vsls.korotaevahomework.presentation.model.EnterState
import ru.vsls.korotaevahomework.presentation.model.FieldEvent

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

    fun loginUser(){

    }

    fun registrationUser(){}

    fun handleFieldChanged(event: FieldEvent){
        when(event){
            is FieldEvent.LoginChanged -> onLoginChanged(event.login)
            is FieldEvent.PasswordChanged -> onPasswordChanged(event.password)
            is FieldEvent.RepeatPasswordChanged -> onRepeatPasswordChanged(event.repeatPassword)
        }
    }

    private fun onRepeatPasswordChanged(repeatPassword: String) {
        val current = _state.value
        if (current !is EnterState.Registration) return

        val isMatch = repeatPassword == current.password

        _state.value = current.copy(
            passwordRepeat = repeatPassword,
            isPasswordError = !isMatch
        )
    }

    private fun onPasswordChanged(password: String) {
        when (val current = _state.value) {

            is EnterState.Login -> {
                _state.value = current.copy(password = password)
            }

            is EnterState.Registration -> {
                val isMatch = password == current.passwordRepeat

                _state.value = current.copy(
                    password = password,
                    isPasswordError = !isMatch
                )
            }

            else -> Unit
        }
    }

    private fun onLoginChanged(login: String) {
        val isValid = login.matches(Regex("^[A-Za-z0-9]*$"))

        when (val current = _state.value) {

            is EnterState.Login -> {
                _state.value = current.copy(
                    login = login,
                    isLoginError = !isValid
                )
            }

            is EnterState.Registration -> {
                _state.value = current.copy(
                    login = login,
                    isLoginError = !isValid
                )
            }

            else -> Unit
        }
    }
}