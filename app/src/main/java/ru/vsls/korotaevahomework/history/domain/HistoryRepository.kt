package ru.vsls.korotaevahomework.history.domain

import ru.vsls.korotaevahomework.form.domain.model.LoanResponse

interface HistoryRepository {
    suspend fun getAllLoans(): List<LoanResponse>
}