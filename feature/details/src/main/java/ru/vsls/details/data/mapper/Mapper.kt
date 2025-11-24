package ru.vsls.details.data.mapper

import ru.vsls.details.utils.formatDate
import ru.vsls.shared.network.data.model.LoanResponseDto
import ru.vsls.shared.network.domain.model.LoanResponse
import ru.vsls.shared.network.domain.model.LoanState
import ru.vsls.utils.FailedStateException


internal fun LoanResponseDto.toDomain() = LoanResponse(
    amount = amount.toInt(),
    firstName = firstName,
    id = id,
    lastName = lastName,
    percent = percent,
    period = period,
    phoneNumber = phoneNumber,
    date = formatDate(date),
    state = getState(state)
)

private fun getState(state: String): LoanState {
    return when (state) {
        "APPROVED" -> LoanState.APPROVED
        "REGISTERED" -> LoanState.REGISTERED
        "REJECTED" -> LoanState.REJECTED
        else -> throw FailedStateException("Unknown state")
    }
}