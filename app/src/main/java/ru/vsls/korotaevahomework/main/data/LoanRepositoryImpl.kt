package ru.vsls.korotaevahomework.main.data

import ru.vsls.korotaevahomework.main.data.mapper.toDomain
import ru.vsls.korotaevahomework.main.domain.LoanRepository
import ru.vsls.korotaevahomework.main.domain.model.Condition

class LoanRepositoryImpl(private val api: LoanApiService) : LoanRepository {
    override suspend fun getConditions(): Condition {
        return api.getConditions().toDomain()
    }
}