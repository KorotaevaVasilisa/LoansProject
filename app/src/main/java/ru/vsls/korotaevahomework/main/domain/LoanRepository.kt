package ru.vsls.korotaevahomework.main.domain

import ru.vsls.korotaevahomework.form.domain.model.LoanResponse
import ru.vsls.korotaevahomework.main.domain.model.Condition

interface LoanRepository {
    suspend fun getConditions(): Condition

    suspend fun getUserLoans(): List<LoanResponse>
}