package ru.vsls.korotaevahomework.form.data.mapper

import ru.vsls.korotaevahomework.form.data.model.LoanRequestDto
import ru.vsls.korotaevahomework.form.data.model.LoanResponseDto
import ru.vsls.korotaevahomework.form.domain.model.EnumState
import ru.vsls.korotaevahomework.form.domain.model.LoanRequest
import ru.vsls.korotaevahomework.form.domain.model.LoanResponse

internal fun LoanRequest.toData() = LoanRequestDto(
    amount = amount,
    firstName = firstName,
    lastName = lastName,
    phoneNumber = phoneNumber,
    percent = percent,
    period = period
)

internal fun LoanResponseDto.toDomain() = LoanResponse(
    amount = amount,
    firstName = firstName,
    id = id,
    lastName = lastName,
    percent = percent,
    period = period,
    phoneNumber = phoneNumber,
    date = date,
    state = getState(state)
)

private fun getState(state: String): EnumState {
    return when (state) {
        "APPROVED" -> EnumState.APPROVED
        "REGISTERED" -> EnumState.REGISTERED
        "REJECTED" -> EnumState.REJECTED
        else -> throw IllegalArgumentException("Unknown state")
    }
}