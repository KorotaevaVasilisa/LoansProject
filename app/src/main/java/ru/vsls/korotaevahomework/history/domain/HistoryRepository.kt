package ru.vsls.korotaevahomework.history.domain

import ru.vsls.shared.network.domain.LoanResponse

interface HistoryRepository {
    suspend fun getAllLoans(): List<LoanResponse>
}