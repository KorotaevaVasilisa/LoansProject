package ru.vsls.korotaevahomework.form.domain

import ru.vsls.korotaevahomework.form.domain.model.LoanRequest
import ru.vsls.shared.network.domain.LoanResponse

interface FormRepository {
    suspend fun sendRequestLoan(request: LoanRequest): LoanResponse
}