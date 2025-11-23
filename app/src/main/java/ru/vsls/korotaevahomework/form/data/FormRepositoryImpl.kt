package ru.vsls.korotaevahomework.form.data

import ru.vsls.korotaevahomework.form.data.mapper.toData
import ru.vsls.korotaevahomework.form.data.mapper.toDomain
import ru.vsls.korotaevahomework.form.domain.FormRepository
import ru.vsls.korotaevahomework.form.domain.model.LoanRequest
import ru.vsls.shared.network.domain.LoanResponse
import javax.inject.Inject

class FormRepositoryImpl @Inject constructor(private val apiService: FormApiService) :
    FormRepository {
    override suspend fun sendRequestLoan(request: LoanRequest): LoanResponse {
        return apiService.postLoanRequest(request.toData()).toDomain()
    }
}