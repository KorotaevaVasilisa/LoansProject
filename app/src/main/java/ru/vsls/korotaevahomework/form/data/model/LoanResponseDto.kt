package ru.vsls.korotaevahomework.form.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoanResponseDto(
    val amount: Double,
    val date: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String,
    val state: String
)
