package ru.vsls.korotaevahomework.main.domain.usecases

import ru.vsls.korotaevahomework.main.domain.LoanRepository

class GetConditionsUseCase(private val repository: LoanRepository) {
    suspend operator fun invoke() = repository.getConditions()
}