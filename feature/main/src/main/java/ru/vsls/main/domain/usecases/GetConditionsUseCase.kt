package ru.vsls.main.domain.usecases

import ru.vsls.main.domain.LoanRepository
import javax.inject.Inject

class GetConditionsUseCase @Inject constructor(private val repository: LoanRepository) {
    suspend operator fun invoke() = repository.getConditions()
}