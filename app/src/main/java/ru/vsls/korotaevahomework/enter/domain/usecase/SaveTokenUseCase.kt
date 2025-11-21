package ru.vsls.korotaevahomework.enter.domain.usecase

import ru.vsls.shared.network.domain.TokenRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(private val repository: TokenRepository) {
    operator fun invoke(token: String) {
        repository.saveToken(token)
    }
}