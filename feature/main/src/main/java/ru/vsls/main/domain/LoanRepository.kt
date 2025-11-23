package ru.vsls.main.domain

import ru.vsls.shared.network.domain.model.LoanResponse
import ru.vsls.main.domain.model.Condition

interface LoanRepository {
    suspend fun getConditions(): Condition

    suspend fun getUserLoans(): List<LoanResponse>
}