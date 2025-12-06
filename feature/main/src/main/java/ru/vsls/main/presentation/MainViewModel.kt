package ru.vsls.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vsls.main.domain.usecases.GetConditionsUseCase
import ru.vsls.main.domain.usecases.GetSomeUserLoanUseCase
import ru.vsls.main.presentation.model.MainState
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

class MainViewModel @Inject constructor(
    private val getConditionsUseCase: GetConditionsUseCase,
    private val getSomeUserLoanUseCase: GetSomeUserLoanUseCase,
    private val router: Router,
) : ViewModel() {
    private var _state = MutableStateFlow<MainState>(MainState.Initial)
    val state = _state.asStateFlow()

    private val _errors = MutableSharedFlow<ErrorType>()
    val errors = _errors.asSharedFlow()

    fun loadData() {

        viewModelScope.launch(exceptionHandler) {

            _state.update { MainState.Loading }

            val conditionsDeferred = async { getConditionsUseCase() }
            val loansDeferred = async { getSomeUserLoanUseCase() }

            val condition = conditionsDeferred.await()
            val loans = loansDeferred.await()

            _state.update {
                MainState.Content(
                    condition = condition,
                    userLoans = loans
                )
            }
        }
    }

    fun onSliderValueChange(value: Float) {
        val state = _state.value as? MainState.Content ?: return

        _state.value = state.copy(valueSlider = value, valueLoan = value.toInt())
    }

    fun navigateToForm() {
        val state = _state.value as? MainState.Content ?: return

        router.navigateTo(
            Screen.FormScreen(
                amount = state.valueLoan,
                period = state.condition.period,
                percent = state.condition.percent
            )
        )
    }

    fun navigateToHistory() {
        router.navigateTo(Screen.HistoryScreen)
    }

    fun navigateToOnboarding() {
        router.navigateTo(Screen.OnboardingScreen)
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->

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