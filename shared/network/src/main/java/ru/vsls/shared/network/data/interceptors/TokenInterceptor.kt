package ru.vsls.shared.network.data.interceptors

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import ru.vsls.shared.network.domain.TokenRepository
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val tokenRepository: TokenRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { tokenRepository.getToken() }

        val newRequest = if (token != null) {
            chain.request().newBuilder()
                .addHeader("Authorization", token)
                .build()
        } else {
            chain.request()
        }

        return chain.proceed(newRequest)
    }
}