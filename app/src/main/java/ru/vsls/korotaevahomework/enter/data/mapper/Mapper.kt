package ru.vsls.korotaevahomework.enter.data.mapper

import ru.vsls.korotaevahomework.enter.data.model.UserDto
import ru.vsls.korotaevahomework.enter.domain.User

fun User.toDto() = UserDto(
    login = login,
    password = password
)