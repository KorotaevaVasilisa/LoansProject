package ru.vsls.korotaevahomework.common.data

import ru.vsls.korotaevahomework.common.data.local.TokenStorage
import ru.vsls.korotaevahomework.common.domain.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(private val tokenStorage: TokenStorage) :
    TokenRepository {
    override fun saveToken(token: String) {
        tokenStorage.saveToken(token)
    }

    override fun getToken(): String? {
        return tokenStorage.getToken()
    }

    override fun deleteToken() {
        tokenStorage.deleteToken()
    }
}