package ru.vsls.main.domain.usecases

import ru.vsls.shared.network.domain.model.LoanResponse
import ru.vsls.main.domain.LoanRepository
import javax.inject.Inject

class GetSomeUserLoanUseCase @Inject constructor(private val repository: LoanRepository) {
    suspend operator fun invoke(): List<LoanResponse> {
        return repository.getUserLoans().take(3)
    }
}