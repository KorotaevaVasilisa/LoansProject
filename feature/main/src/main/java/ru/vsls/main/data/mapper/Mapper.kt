package ru.vsls.main.data.mapper

import ru.vsls.main.data.model.ConditionDto
import ru.vsls.main.domain.model.Condition

internal fun ConditionDto.toDomain() = Condition(
    maxAmount = maxAmount,
    percent = percent,
    period = period
)