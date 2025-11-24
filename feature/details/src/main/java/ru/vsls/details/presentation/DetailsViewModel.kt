package ru.vsls.details.presentation

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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vsls.details.domain.usecase.GetLoanUseCase
import ru.vsls.details.presentation.model.DetailsState
import ru.vsls.navigation.Router
import ru.vsls.utils.BadRequestException
import ru.vsls.utils.ErrorType
import ru.vsls.utils.FailedStateException
import ru.vsls.utils.NoInternetException
import ru.vsls.utils.NonValidFieldsException
import ru.vsls.utils.NotFoundException
import ru.vsls.utils.ServerException
import ru.vsls.utils.UnauthorizedException
import ru.vsls.utils.UnknownException
import ru.vsls.utils.UnknownLoanException

class DetailsViewModel @AssistedInject constructor(
    @Assisted val loanId: Int?,
    private val getLoanUseCase: GetLoanUseCase,
    private val router: Router,
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(loanId: Int?): DetailsViewModel
    }

    private var _state = MutableStateFlow<DetailsState>(DetailsState.Initial)
    val state = _state.asStateFlow()

    private val _errors = MutableSharedFlow<ErrorType>()
    val errors = _errors.asSharedFlow()

    fun loadDetails() {
        _state.value = DetailsState.Loading
        viewModelScope.launch(exceptionHandler) {

            if (loanId == null) throw UnknownLoanException("Loan ID is null")

            val loan = getLoanUseCase(loanId)
            _state.update { DetailsState.Content(loan) }

        }
    }

    fun onBackPressed() {
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
            is UnknownLoanException -> ErrorType.UNKNOWN_LOAN
            else -> ErrorType.UNKNOWN
        }

        viewModelScope.launch {
            _errors.emit(errorType)
        }
    }
}