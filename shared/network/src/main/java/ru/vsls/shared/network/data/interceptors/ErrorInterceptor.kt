package ru.vsls.shared.network.data.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import ru.vsls.utils.BadRequestException
import ru.vsls.utils.NoInternetException
import ru.vsls.utils.NotFoundException
import ru.vsls.utils.ServerException
import ru.vsls.utils.UnauthorizedException
import ru.vsls.utils.UnknownException
import java.io.IOException
import java.net.HttpURLConnection.HTTP_BAD_REQUEST
import java.net.HttpURLConnection.HTTP_NOT_FOUND
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED
import javax.inject.Inject

class ErrorInterceptor @Inject constructor(private val networkMonitor: NetworkMonitor) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkMonitor.isOnline()) {
            throw NoInternetException("No internet connection")
        }

        return runCatching { chain.proceed(chain.request()) }
            .mapCatching { response ->
                response.takeIf { it.isSuccessful }
                    ?: throw createException(response)
            }
            .getOrThrow()
    }

    private fun createException(response: Response): IOException =
        when (response.code) {
            HTTP_NOT_FOUND -> NotFoundException(response.message)
            HTTP_BAD_REQUEST -> BadRequestException(response.message)
            HTTP_UNAUTHORIZED -> UnauthorizedException(response.message)
            in 500..599 -> ServerException("Server error: ${response.code}")
            else -> UnknownException(response.message)
        }

}