package ru.vsls.details.domain

import ru.vsls.shared.network.domain.model.LoanResponse

interface DetailsRepository {

    suspend fun getLoan(id: Int): LoanResponse
}