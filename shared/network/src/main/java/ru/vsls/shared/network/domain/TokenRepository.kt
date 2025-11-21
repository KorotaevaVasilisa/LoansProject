package ru.vsls.shared.network.domain

interface TokenRepository {

    fun saveToken(token: String)

    fun getToken(): String?

    fun deleteToken()
}