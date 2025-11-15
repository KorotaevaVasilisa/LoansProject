package ru.vsls.korotaevahomework.domain.usecase

import ru.vsls.korotaevahomework.domain.EnterRepository
import ru.vsls.korotaevahomework.domain.User

class LoginUserUseCase(private val repository: EnterRepository) {
    suspend operator fun invoke(login: String, password: String) =
        repository.loginUser(User(login, password))
}