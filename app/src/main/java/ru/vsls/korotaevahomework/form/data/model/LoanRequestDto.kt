package ru.vsls.korotaevahomework.form.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoanRequestDto(
    val amount: Int,
    val firstName: String,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String,
)
