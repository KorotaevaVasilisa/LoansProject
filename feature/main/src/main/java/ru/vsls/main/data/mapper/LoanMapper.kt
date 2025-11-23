package ru.vsls.main.data.mapper

import ru.vsls.main.utils.formatDateShort
import ru.vsls.shared.network.data.model.LoanResponseDto
import ru.vsls.shared.network.domain.model.LoanResponse
import ru.vsls.shared.network.domain.model.LoanState

internal fun LoanResponseDto.toDomain() = LoanResponse(
    amount = amount.toInt(),
    firstName = firstName,
    id = id,
    lastName = lastName,
    percent = percent,
    period = period,
    phoneNumber = phoneNumber,
    date = formatDateShort(date),
    state = getState(state)
)

private fun getState(state: String): LoanState {
    return when (state) {
        "APPROVED" -> LoanState.APPROVED
        "REGISTERED" -> LoanState.REGISTERED
        "REJECTED" -> LoanState.REJECTED
        else -> throw IllegalArgumentException("Unknown state")
    }
}