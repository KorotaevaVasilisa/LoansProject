package ru.vsls.korotaevahomework.form

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class FormViewModel @AssistedInject constructor(
    @Assisted("amount") val amount: Int?,
    @Assisted("percent") val percent: Double?,
    @Assisted("period") val period: Int?,
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
        return amount != null && percent != null && period != null
    }
}