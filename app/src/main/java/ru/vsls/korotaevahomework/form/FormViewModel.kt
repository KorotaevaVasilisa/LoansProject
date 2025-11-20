package ru.vsls.korotaevahomework.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ru.vsls.korotaevahomework.form.domain.usecase.SendRequestUseCase

class FormViewModel @AssistedInject constructor(
    @Assisted("amount") val amount: Int?,
    @Assisted("percent") val percent: Double?,
    @Assisted("period") val period: Int?,
    private val sendRequestUseCase: SendRequestUseCase,
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
        }
        return true
    }
}