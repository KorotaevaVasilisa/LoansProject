package ru.vsls.korotaevahomework.main.domain.usecases

import ru.vsls.korotaevahomework.main.domain.LoanRepository
import javax.inject.Inject

class GetConditionsUseCase @Inject constructor(private val repository: LoanRepository) {
    suspend operator fun invoke() = repository.getConditions()
}