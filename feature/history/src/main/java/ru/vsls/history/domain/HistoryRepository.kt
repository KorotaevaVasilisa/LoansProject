package ru.vsls.history.domain

import ru.vsls.shared.network.domain.model.LoanResponse

interface HistoryRepository {
    suspend fun getAllLoans(): List<LoanResponse>
}