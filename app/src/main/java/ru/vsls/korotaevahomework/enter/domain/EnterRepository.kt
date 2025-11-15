package ru.vsls.korotaevahomework.enter.domain

interface EnterRepository {
    suspend fun loginUser(user: User): String
    suspend fun registerUser(user: User)
}