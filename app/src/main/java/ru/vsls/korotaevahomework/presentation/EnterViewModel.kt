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
        TODO("Not yet implemented")
    }

    private fun onPasswordChanged(password: String) {
        TODO("Not yet implemented")
    }

    private fun onLoginChanged(login: String) {
        TODO("Not yet implemented")
    }
}