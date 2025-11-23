package ru.vsls.korotaevahomework.details.data

import ru.vsls.korotaevahomework.details.domain.DetailsRepository
import ru.vsls.korotaevahomework.form.data.mapper.toDomain
import ru.vsls.shared.network.domain.model.LoanResponse
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(private val apiService: DetailsApiService) :
    DetailsRepository {
    override suspend fun getLoan(id: Int): LoanResponse {
        return apiService.getLoan(id).toDomain()
    }
}