package ru.vsls.korotaevahomework.enter.data

import retrofit2.http.Body
import retrofit2.http.POST
import ru.vsls.korotaevahomework.enter.data.model.UserDto

interface EnterApiService {
    @POST("/login")
    suspend fun loginUser(@Body user: UserDto):String

    @POST("/registration")
    suspend fun registrationUser(@Body user: UserDto)
}