package ru.vsls.korotaevahomework.enter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vsls.korotaevahomework.enter.domain.usecase.GetTokenUseCase
import ru.vsls.korotaevahomework.enter.domain.usecase.LoginUserUseCase
import ru.vsls.korotaevahomework.enter.domain.usecase.RegistrationUserUseCase
import ru.vsls.korotaevahomework.enter.domain.usecase.SaveTokenUseCase
import ru.vsls.korotaevahomework.enter.presentation.model.EnterState
import ru.vsls.korotaevahomework.enter.presentation.model.FieldEvent
import javax.inject.Inject

class EnterViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    private val registrationUserUseCase: RegistrationUserUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val getTokenUseCase: GetTokenUseCase,
) : ViewModel() {
    private var _state = MutableStateFlow<EnterState>(EnterState.Initial)
    val state = _state.asStateFlow()

    private var previousState: EnterState? = null
    private val _errors = MutableSharedFlow<String>()
    val errors = _errors.asSharedFlow()

    private val _navigationChannel = Channel<EnterNavigationRoute>(Channel.BUFFERED)
    val navigation = _navigationChannel.receiveAsFlow()
    fun initForm() {
        _state.update { EnterState.Loading }
        val token = getTokenUseCase()
        if (token.isNullOrEmpty()) {
            _state.update { EnterState.Login() }
        } else {
            viewModelScope.launch {
                _navigationChannel.send(EnterNavigationRoute.Main)
            }
        }
    }

    fun switchToLogin() {
        _state.update { EnterState.Login() }
    }

    fun switchToRegistration() {
        _state.update { EnterState.Registration() }
    }

    fun loginUser() {
        val state = _state.value as? EnterState.Login ?: return

        previousState = state
        _state.update { EnterState.Loading }
        viewModelScope.launch(exceptionHandler) {
            val response = loginUserUseCase(state.login, state.password)
            val token = response.string()
            saveTokenUseCase(token)
            viewModelScope.launch {
                _navigationChannel.send(EnterNavigationRoute.Main)
            }
        }
    }

    fun registrationUser() {
        val current = _state.value as? EnterState.Registration ?: return

        previousState = current
        _state.value = EnterState.Loading

        viewModelScope.launch(exceptionHandler) {
            registrationUserUseCase(
                current.login,
                current.password
            )
            val response = loginUserUseCase(current.login, current.password)
            val token = response.string()
            saveTokenUseCase(token)
            viewModelScope.launch {
                _navigationChannel.send(EnterNavigationRoute.Main)
            }
        }
    }

    fun handleFieldChanged(event: FieldEvent) {
        when (event) {
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

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        previousState?.let { safePrev ->
            _state.value = safePrev
        }

        viewModelScope.launch {
            _errors.emit(throwable.message ?: "Unknown error")
        }
    }
}

sealed interface EnterNavigationRoute {
    data object Main : EnterNavigationRoute
}