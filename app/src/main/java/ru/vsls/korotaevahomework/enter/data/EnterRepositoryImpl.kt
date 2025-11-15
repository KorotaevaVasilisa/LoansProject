package ru.vsls.korotaevahomework.enter.data

import ru.vsls.korotaevahomework.enter.data.mapper.toDto
import ru.vsls.korotaevahomework.enter.domain.EnterRepository
import ru.vsls.korotaevahomework.enter.domain.User
import javax.inject.Inject

class EnterRepositoryImpl @Inject constructor(private val apiService: EnterApiService) :
    EnterRepository {
    override suspend fun loginUser(user: User): String {
        return apiService.loginUser(user.toDto())
    }

    override suspend fun registerUser(user: User) {
        apiService.registrationUser(user.toDto())
    }
}