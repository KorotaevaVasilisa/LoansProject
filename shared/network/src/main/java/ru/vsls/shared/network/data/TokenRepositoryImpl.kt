package ru.vsls.shared.network.data

import ru.vsls.shared.network.data.local.TokenStorage
import ru.vsls.shared.network.domain.TokenRepository
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