package ru.vsls.korotaevahomework.main.data

import ru.vsls.korotaevahomework.form.data.mapper.toDomain
import ru.vsls.shared.network.domain.LoanResponse
import ru.vsls.korotaevahomework.main.data.mapper.toDomain
import ru.vsls.korotaevahomework.main.domain.LoanRepository
import ru.vsls.korotaevahomework.main.domain.model.Condition
import javax.inject.Inject

class LoanRepositoryImpl @Inject constructor(private val api: LoanApiService) : LoanRepository {
    override suspend fun getConditions(): Condition {
        return api.getConditions().toDomain()
    }

    override suspend fun getUserLoans(): List<LoanResponse> {
        return api.getUserLoans().map { it.toDomain() }
    }
}