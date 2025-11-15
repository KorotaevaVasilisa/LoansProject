package ru.vsls.korotaevahomework.enter.data.mapper

import ru.vsls.korotaevahomework.enter.data.model.UserDto
import ru.vsls.korotaevahomework.enter.domain.User

fun User.toDto() = UserDto(
    name = login,
    password = password
)