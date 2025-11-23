package ru.vsls.korotaevahomework.history.data

import retrofit2.http.GET
import ru.vsls.shared.network.data.model.LoanResponseDto

interface HistoryApiService {
    @GET("/loans/all")
    suspend fun getAllLoans(): List<LoanResponseDto>
}