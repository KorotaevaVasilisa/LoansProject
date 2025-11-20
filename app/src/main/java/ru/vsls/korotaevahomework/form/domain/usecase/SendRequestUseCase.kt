package ru.vsls.korotaevahomework.form.domain.usecase

import ru.vsls.korotaevahomework.form.domain.FormRepository
import ru.vsls.korotaevahomework.form.domain.model.LoanRequest
import ru.vsls.korotaevahomework.form.domain.model.LoanResponse

class SendRequestUseCase(private val repository: FormRepository) {
    suspend operator fun invoke(
        name: String,
        surname: String,
        number: String,
        percent: Double,
        amount: Int,
        period: Int,
    ): LoanResponse {
        val request = LoanRequest(
            amount = amount,
            percent = percent,
            period = period,
            firstName = name,
            lastName = surname,
            phoneNumber = number
        )
        return repository.sendRequestLoan(request)
    }
}