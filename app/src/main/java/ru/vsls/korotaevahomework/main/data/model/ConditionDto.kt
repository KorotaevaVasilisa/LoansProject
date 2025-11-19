package ru.vsls.korotaevahomework.main.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ConditionDto(
    val maxAmount: Int,
    val percent: Double,
    val period: Int,
)
