package ru.vsls.korotaevahomework.screens.menu.domain

import ru.vsls.shared.network.domain.TokenRepository
import javax.inject.Inject

class DeleteTokenUseCase @Inject constructor(private val tokenRepository: TokenRepository) :
        () -> Unit by tokenRepository::deleteToken