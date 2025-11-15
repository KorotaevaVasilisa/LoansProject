package ru.vsls.korotaevahomework.enter.domain

import okhttp3.ResponseBody

interface EnterRepository {
    suspend fun loginUser(user: User): ResponseBody
    suspend fun registerUser(user: User)
}