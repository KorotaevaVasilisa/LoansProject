package ru.vsls.korotaevahomework.enter.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val login: String,
    val password: String,
)
