package ru.vsls.enter.data

import okhttp3.ResponseBody
import ru.vsls.enter.data.mapper.toDto
import ru.vsls.enter.domain.EnterRepository
import ru.vsls.enter.domain.User
import javax.inject.Inject

class EnterRepositoryImpl @Inject constructor(private val apiService: EnterApiService) :
    EnterRepository {
    override suspend fun loginUser(user: User): ResponseBody {
        return apiService.loginUser(user.toDto())
    }

    override suspend fun registerUser(user: User) {
        apiService.registrationUser(user.toDto())
    }
}