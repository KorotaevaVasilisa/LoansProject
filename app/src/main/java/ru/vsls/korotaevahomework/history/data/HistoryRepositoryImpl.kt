package ru.vsls.korotaevahomework.history.data

import ru.vsls.korotaevahomework.form.data.mapper.toDomain
import ru.vsls.korotaevahomework.form.domain.model.LoanResponse
import ru.vsls.korotaevahomework.history.domain.HistoryRepository

class HistoryRepositoryImpl(private val apiService: HistoryApiService) : HistoryRepository {

    override suspend fun getAllLoans(): List<LoanResponse> {
        return apiService.getAllLoans().map { it.toDomain() }
    }

}