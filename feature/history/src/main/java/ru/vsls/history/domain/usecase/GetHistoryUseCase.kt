package ru.vsls.history.domain.usecase

import ru.vsls.shared.network.domain.model.LoanResponse
import ru.vsls.history.domain.HistoryRepository
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(private val repository: HistoryRepository) :
        suspend () -> List<LoanResponse> by repository::getAllLoans
