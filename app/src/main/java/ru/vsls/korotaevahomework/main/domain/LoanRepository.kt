package ru.vsls.korotaevahomework.main.domain

import ru.vsls.shared.network.domain.LoanResponse
import ru.vsls.korotaevahomework.main.domain.model.Condition

interface LoanRepository {
    suspend fun getConditions(): Condition

    suspend fun getUserLoans(): List<LoanResponse>
}