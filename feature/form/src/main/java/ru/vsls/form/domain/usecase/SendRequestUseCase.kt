package ru.vsls.form.domain.usecase

import ru.vsls.form.domain.FormRepository
import ru.vsls.shared.network.domain.model.LoanRequest
import ru.vsls.shared.network.domain.model.LoanResponse
import javax.inject.Inject

class SendRequestUseCase @Inject constructor(private val repository: FormRepository) {
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