package ru.vsls.korotaevahomework.details.data

import ru.vsls.korotaevahomework.details.domain.DetailsRepository
import ru.vsls.korotaevahomework.form.data.mapper.toDomain
import ru.vsls.korotaevahomework.form.domain.model.LoanResponse

class DetailsResponseImpl(private val apiService: DetailsApiService) : DetailsRepository {
    override suspend fun getLoan(id: Int): LoanResponse {
        return apiService.getLoan(id).toDomain()
    }
}