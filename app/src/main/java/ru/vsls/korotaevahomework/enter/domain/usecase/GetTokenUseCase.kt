package ru.vsls.korotaevahomework.enter.domain.usecase

import ru.vsls.shared.network.domain.TokenRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(private val repository: TokenRepository) {
    operator fun invoke(): String? {
        return repository.getToken()
    }
}