package ru.vsls.form.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.vsls.form.domain.usecase.SendRequestUseCase
import ru.vsls.form.presentation.model.FormState
import ru.vsls.navigation.Router
import ru.vsls.navigation.Screen
import ru.vsls.shared.network.domain.model.LoanState
import ru.vsls.utils.BadRequestException
import ru.vsls.utils.ErrorType
import ru.vsls.utils.FailedStateException
import ru.vsls.utils.NoInternetException
import ru.vsls.utils.NonValidFieldsException
import ru.vsls.utils.NotFoundException
import ru.vsls.utils.ServerException
import ru.vsls.utils.UnauthorizedException
import ru.vsls.utils.UnknownException

class FormViewModel @AssistedInject constructor(
    @Assisted("amount") val amount: Int?,
    @Assisted("percent") val percent: Double?,
    @Assisted("period") val period: Int?,
    private val sendRequestUseCase: SendRequestUseCase,
    private val router: Router,
) : ViewModel() {

    private var _state = MutableStateFlow<FormState>(FormState())
    val state = _state.asStateFlow()

    private val _errors = MutableSharedFlow<ErrorType>()
    val errors = _errors.asSharedFlow()

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("amount") amount: Int?,
            @Assisted("percent") percent: Double?,
            @Assisted("period") period: Int?,
        ): FormViewModel
    }

    fun registerLoan() {
        val current = _state.value
        _state.value = current.copy(isLoading = true)

        viewModelScope.launch(exceptionHandler) {

            if (amount == null || percent == null || period == null)
                throw FailedStateException("Failed State")

            validateFields()

            val request = sendRequestUseCase(
                name = current.name,
                surname = current.surname,
                number = current.phone,
                amount = amount,
                percent = percent,
                period = period
            )
            val state = request.state
            val success = (state != LoanState.REJECTED)
            router.clearBackStackAndNavigate(Screen.ResultScreen(success, request.amount))
        }
    }

    fun onNameChanged(value: String) {
        val error = validateRussian(value)

        _state.value = _state.value.copy(
            name = value,
            nameError = error
        )
    }

    fun onSurnameChanged(value: String) { val error = validateRussian(value)

        _state.value = _state.value.copy(
            surname = value,
            surnameError = error
        )
    }

    fun onPhoneChanged(value: String) {
        val error = validatePhone(value)

        _state.value = _state.value.copy(
            phone = value,
            phoneError = error
        )
    }

    private fun validateRussian(text: String): ErrorType? {
        return when {
            text.isBlank() -> ErrorType.EMPTY_FIELD
            !text.matches(Regex("^[а-яА-ЯёЁ\\s-]+$")) -> ErrorType.NON_RUSSIAN_SYMBOLS
            else -> null
        }
    }

    private fun validatePhone(text: String): ErrorType? {
        return when {
            text.isBlank() -> ErrorType.EMPTY_FIELD
            text.length != 11 -> ErrorType.NON_VALID_PHONE
            else -> null
        }
    }

    fun validateFields() {
        val currentState = _state.value
        val validName = validateRussian(currentState.name)
        val validSurname = validateRussian(currentState.surname)
        val validPhone = validatePhone(currentState.phone)

        if (validName != null || validSurname != null || validPhone != null)
            throw NonValidFieldsException("Non valid fields")
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _state.value = _state.value.copy(isLoading = false)

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

    fun popBackStack() {
        router.navigateBack()
    }
}