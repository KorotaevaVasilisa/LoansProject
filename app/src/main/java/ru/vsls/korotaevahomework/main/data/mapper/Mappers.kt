package ru.vsls.korotaevahomework.main.data.mapper

import ru.vsls.korotaevahomework.main.data.model.ConditionDto
import ru.vsls.korotaevahomework.main.domain.model.Condition

internal fun ConditionDto.toDomain() = Condition(
    maxAmount = maxAmount,
    percent = percent,
    period = period
)