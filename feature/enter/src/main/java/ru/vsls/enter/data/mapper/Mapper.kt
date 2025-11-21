package ru.vsls.enter.data.mapper

import ru.vsls.enter.data.model.UserDto
import ru.vsls.enter.domain.User

fun User.toDto() = UserDto(
    name = login,
    password = password
)