package ru.vsls.korotaevahomework.data.mapper

import ru.vsls.korotaevahomework.data.model.UserDto
import ru.vsls.korotaevahomework.domain.User

fun User.toDto() = UserDto(
    login = login,
    password = password
)