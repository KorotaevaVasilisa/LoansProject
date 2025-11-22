package ru.vsls.korotaevahomework.history.data

import ru.vsls.korotaevahomework.form.data.mapper.toDomain
import ru.vsls.korotaevahomework.form.domain.model.LoanResponse
import ru.vsls.korotaevahomework.history.domain.HistoryRepository
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(private val apiService: HistoryApiService) :
    HistoryRepository {

    override suspend fun getAllLoans(): List<LoanResponse> {
        return apiService.getAllLoans().map { it.toDomain() }
    }

}