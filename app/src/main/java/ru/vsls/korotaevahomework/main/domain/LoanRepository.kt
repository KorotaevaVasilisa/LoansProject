package ru.vsls.korotaevahomework.main.domain

import ru.vsls.korotaevahomework.main.domain.model.Condition

interface LoanRepository {
    suspend fun getConditions(): Condition
}