package ru.vsls.main.data

import ru.vsls.shared.network.domain.model.LoanResponse
import ru.vsls.main.data.mapper.toDomain
import ru.vsls.main.domain.LoanRepository
import ru.vsls.main.domain.model.Condition
import javax.inject.Inject

class LoanRepositoryImpl @Inject constructor(private val api: LoanApiService) : LoanRepository {
    override suspend fun getConditions(): Condition {
        return api.getConditions().toDomain()
    }

    override suspend fun getUserLoans(): List<LoanResponse> {
        return api.getUserLoans().map { it.toDomain() }
    }
}