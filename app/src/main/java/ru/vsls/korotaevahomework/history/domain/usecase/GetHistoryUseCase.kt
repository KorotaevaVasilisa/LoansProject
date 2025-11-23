package ru.vsls.korotaevahomework.history.domain.usecase

import ru.vsls.shared.network.domain.LoanResponse
import ru.vsls.korotaevahomework.history.domain.HistoryRepository
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(private val repository: HistoryRepository) :
        suspend () -> List<LoanResponse> by repository::getAllLoans
