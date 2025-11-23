package ru.vsls.korotaevahomework.form.domain

import ru.vsls.shared.network.domain.model.LoanRequest
import ru.vsls.shared.network.domain.model.LoanResponse

interface FormRepository {
    suspend fun sendRequestLoan(request: LoanRequest): LoanResponse
}