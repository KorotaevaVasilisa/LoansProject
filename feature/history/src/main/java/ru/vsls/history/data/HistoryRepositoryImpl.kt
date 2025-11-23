package ru.vsls.history.data

import ru.vsls.history.data.mapper.toDomain
import ru.vsls.history.domain.HistoryRepository
import ru.vsls.shared.network.domain.model.LoanResponse
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(private val apiService: HistoryApiService) :
    HistoryRepository {

    override suspend fun getAllLoans(): List<LoanResponse> {
        return apiService.getAllLoans().map { it.toDomain() }
    }

}