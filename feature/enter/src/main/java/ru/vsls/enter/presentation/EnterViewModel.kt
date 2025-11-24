package ru.vsls.enter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vsls.enter.domain.usecase.GetTokenUseCase
import ru.vsls.enter.domain.usecase.LoginUserUseCase
import ru.vsls.enter.domain.usecase.RegistrationUserUseCase
import ru.vsls.enter.domain.usecase.SaveTokenUseCase
import ru.vsls.enter.presentation.model.EnterState
import ru.vsls.enter.presentation.model.FieldEvent
import ru.vsls.navigation.Router
import ru.vsls.navigation.Screen
import ru.vsls.utils.BadRequestException
import ru.vsls.utils.ErrorType
import ru.vsls.utils.FailedStateException
import ru.vsls.utils.NoInternetException
import ru.vsls.utils.NonValidFieldsException
import ru.vsls.utils.NotFoundException
import ru.vsls.utils.ServerException
import ru.vsls.utils.UnauthorizedException
import ru.vsls.utils.UnknownException
import javax.inject.Inject

class EnterViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    private val registrationUserUseCase: RegistrationUserUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val route: Router,
) : ViewModel() {
    private var _state = MutableStateFlow<EnterState>(EnterState.Initial)
    val state = _state.asStateFlow()

    private var previousState: EnterState? = null
    private val _errors = MutableSharedFlow<ErrorType>()
    val errors = _errors.asSharedFlow()

    fun initForm() {
        if (_state.value is EnterState.Login || _state.value is EnterState.Registration) return

        _state.update { EnterState.Loading }
        val token = getTokenUseCase()
        if (token.isNullOrEmpty()) {
            _state.update { EnterState.Login() }
        } else {
            route.replaceFragment(Screen.MainScreen)
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
            validateForm(current = state)
            val response = loginUserUseCase(state.login, state.password)
            val token = response.string()
            saveTokenUseCase(token)
            route.replaceFragment(Screen.MainScreen)
        }
    }

    fun registrationUser() {
        val current = _state.value as? EnterState.Registration ?: return

        previousState = current
        _state.value = EnterState.Loading

        viewModelScope.launch(exceptionHandler) {
            validateForm(current)

            registrationUserUseCase(
                current.login,
                current.password
            )
            val response = loginUserUseCase(current.login, current.password)
            val token = response.string()
            saveTokenUseCase(token)
            route.replaceFragment(Screen.OnboardingScreen)
        }
    }

    fun handleFieldChanged(event: FieldEvent) {
        when (event) {
            is FieldEvent.LoginChanged -> onLoginChanged(event.login)
            is FieldEvent.PasswordChanged -> onPasswordChanged(event.password)
            is FieldEvent.RepeatPasswordChanged -> onRepeatPasswordChanged(event.repeatPassword)
        }
    }

    private fun validateForm(current: EnterState) {
        when (current) {
            EnterState.Initial,
            EnterState.Loading,
                -> throw FailedStateException("Failed state")

            is EnterState.Login -> {
                val isLoginError = current.login.isBlank()
                val isPasswordError = current.password.isBlank()

                if ((isLoginError || isPasswordError))
                    throw NonValidFieldsException("Non valid fields")
            }

            is EnterState.Registration -> {
                val isNameError = current.login.isBlank()
                val isSurnameError = current.password.isBlank()
                val isDescriptionError = current.passwordRepeat.isBlank()

                val isFormValid =
                    !(isNameError || isSurnameError || isDescriptionError)

                if (!isFormValid)
                    throw NonValidFieldsException("Non valid fields")
            }
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

        val errorType = when (throwable) {
            is NotFoundException -> ErrorType.USER_NOT_FOUND
            is UnknownException -> ErrorType.UNKNOWN
            is NoInternetException -> ErrorType.NO_INTERNET
            is UnauthorizedException -> ErrorType.UNAUTHORIZED
            is BadRequestException -> ErrorType.BAD_REQUEST
            is ServerException -> ErrorType.SERVER
            is NonValidFieldsException -> ErrorType.NON_VALID
            is FailedStateException -> ErrorType.FAILED_STATE
            else -> ErrorType.UNKNOWN
        }

        viewModelScope.launch {
            _errors.emit(errorType)
        }
    }
}
