package ru.vsls.korotaevahomework.history.data.mapper

import ru.vsls.korotaevahomework.common.utils.formatDateShort
import ru.vsls.korotaevahomework.form.data.model.LoanRequestDto
import ru.vsls.shared.network.data.model.LoanResponseDto
import ru.vsls.shared.network.domain.LoanState
import ru.vsls.korotaevahomework.form.domain.model.LoanRequest
import ru.vsls.shared.network.domain.LoanResponse

internal fun LoanRequest.toData() = LoanRequestDto(
    amount = amount,
    firstName = firstName,
    lastName = lastName,
    phoneNumber = phoneNumber,
    percent = percent,
    period = period
)

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