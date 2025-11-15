package ru.vsls.korotaevahomework.enter.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val name: String,
    val password: String,
)
