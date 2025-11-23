package ru.vsls.korotaevahomework.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ru.vsls.korotaevahomework.form.domain.usecase.SendRequestUseCase
import ru.vsls.navigation.Router
import ru.vsls.navigation.Screen
import ru.vsls.shared.network.domain.model.LoanState

class FormViewModel @AssistedInject constructor(
    @Assisted("amount") val amount: Int?,
    @Assisted("percent") val percent: Double?,
    @Assisted("period") val period: Int?,
    private val sendRequestUseCase: SendRequestUseCase,
    private val router: Router,
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("amount") amount: Int?,
            @Assisted("percent") percent: Double?,
            @Assisted("period") period: Int?,
        ): FormViewModel
    }

    fun registerLoan(name: String, surname: String, number: String): Boolean {
        if (amount == null || percent == null || period == null) {
            return false
        }

        viewModelScope.launch {
            val request = sendRequestUseCase(
                name = name,
                surname = surname,
                number = number,
                amount = amount,
                percent = percent,
                period = period
            )
            val state = request.state
            val success = (state != LoanState.REJECTED)
            router.clearBackStackAndNavigate(Screen.ResultScreen(success, request.amount))
        }
        return true
    }

    fun popBackStack() {
        router.navigateBack()
    }
}