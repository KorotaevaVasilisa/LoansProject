package ru.vsls.korotaevahomework.domain.usecase

import ru.vsls.korotaevahomework.domain.EnterRepository
import ru.vsls.korotaevahomework.domain.User
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(private val repository: EnterRepository) {
    suspend operator fun invoke(login: String, password: String) =
        repository.loginUser(User(login, password))
}