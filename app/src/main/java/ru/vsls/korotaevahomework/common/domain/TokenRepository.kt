package ru.vsls.korotaevahomework.common.domain

interface TokenRepository {

    fun saveToken(token: String)

    fun getToken(): String?

    fun deleteToken()
}