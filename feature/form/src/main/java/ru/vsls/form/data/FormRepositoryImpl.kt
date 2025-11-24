package ru.vsls.form.data

import ru.vsls.form.data.mapper.toData
import ru.vsls.form.data.mapper.toDomain
import ru.vsls.form.domain.FormRepository
import ru.vsls.shared.network.domain.model.LoanRequest
import ru.vsls.shared.network.domain.model.LoanResponse
import javax.inject.Inject

class FormRepositoryImpl @Inject constructor(private val apiService: FormApiService) :
    FormRepository {
    override suspend fun sendRequestLoan(request: LoanRequest): LoanResponse {
        return apiService.postLoanRequest(request.toData()).toDomain()
    }
}