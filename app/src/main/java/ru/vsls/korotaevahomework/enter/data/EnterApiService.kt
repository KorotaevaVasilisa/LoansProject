package ru.vsls.korotaevahomework.enter.data

import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST
import ru.vsls.korotaevahomework.enter.data.model.UserDto

interface EnterApiService {
    @POST("/login")
    suspend fun loginUser(@Body user: UserDto): ResponseBody

    @POST("/registration")
    suspend fun registrationUser(@Body user: UserDto)
}