package ru.vsls.history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.vsls.history.domain.usecase.GetHistoryUseCase
import ru.vsls.history.presentation.model.HistoryState
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

class HistoryViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase,
    private val router: Router,
) :
    ViewModel() {
    private var _state = MutableStateFlow<HistoryState>(HistoryState.Initial)
    val state = _state.asStateFlow()

    private val _errors = MutableSharedFlow<ErrorType>()
    val errors = _errors.asSharedFlow()
    fun loadHistory() {
        if (_state.value is HistoryState.Content) return

        _state.value = HistoryState.Loading
        viewModelScope.launch(exceptionHandler) {
            val loans = getHistoryUseCase()
            _state.value = HistoryState.Content(loans)
        }

    }

    fun navigateToDetails(id: Int) {
        router.navigateTo(Screen.DetailsScreen(id))
    }

    fun navigateToBack() {
        router.navigateBack()
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