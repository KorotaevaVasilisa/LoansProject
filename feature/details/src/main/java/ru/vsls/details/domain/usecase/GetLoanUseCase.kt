package ru.vsls.details.domain.usecase

import ru.vsls.details.domain.DetailsRepository
import ru.vsls.shared.network.domain.model.LoanResponse
import javax.inject.Inject

class GetLoanUseCase @Inject constructor(private val repository: DetailsRepository) :
    suspend (Int) -> LoanResponse by repository::getLoan