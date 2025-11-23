package ru.vsls.korotaevahomework.details.domain

import ru.vsls.shared.network.domain.LoanResponse

interface DetailsRepository {

    suspend fun getLoan(id: Int): LoanResponse
}