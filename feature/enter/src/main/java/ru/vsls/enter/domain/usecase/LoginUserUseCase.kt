package ru.vsls.enter.domain.usecase

import ru.vsls.enter.domain.EnterRepository
import ru.vsls.enter.domain.User
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(private val repository: EnterRepository) {
    suspend operator fun invoke(login: String, password: String) =
        repository.loginUser(User(login, password))
}