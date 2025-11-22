package ru.vsls.korotaevahomework.history.domain.usecase

import ru.vsls.korotaevahomework.form.domain.model.LoanResponse
import ru.vsls.korotaevahomework.history.domain.HistoryRepository
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(private val repository: HistoryRepository) :
        suspend () -> List<LoanResponse> by repository::getAllLoans
