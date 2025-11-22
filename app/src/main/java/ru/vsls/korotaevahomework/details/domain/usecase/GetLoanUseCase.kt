package ru.vsls.korotaevahomework.details.domain.usecase

import ru.vsls.korotaevahomework.details.domain.DetailsRepository
import ru.vsls.korotaevahomework.form.domain.model.LoanResponse

class GetLoanUseCase(private val repository: DetailsRepository) :
    suspend (Int) -> LoanResponse by repository::getLoan