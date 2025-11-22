package ru.vsls.korotaevahomework.details.domain.usecase

import ru.vsls.korotaevahomework.details.domain.DetailsRepository
import ru.vsls.korotaevahomework.form.domain.model.LoanResponse
import javax.inject.Inject

class GetLoanUseCase @Inject constructor(private val repository: DetailsRepository) :
    suspend (Int) -> LoanResponse by repository::getLoan