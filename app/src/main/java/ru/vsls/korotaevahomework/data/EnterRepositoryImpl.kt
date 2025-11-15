package ru.vsls.korotaevahomework.data

import ru.vsls.korotaevahomework.data.mapper.toDto
import ru.vsls.korotaevahomework.domain.EnterRepository
import ru.vsls.korotaevahomework.domain.User

class EnterRepositoryImpl(private val apiService: EnterApiService) : EnterRepository {
    override suspend fun loginUser(user: User): String {
        return apiService.loginUser(user.toDto())
    }

    override suspend fun registerUser(user: User) {
        apiService.registrationUser(user.toDto())
    }
}