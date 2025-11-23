package ru.vsls.korotaevahomework.main.data

import retrofit2.http.GET
import ru.vsls.shared.network.data.model.LoanResponseDto
import ru.vsls.korotaevahomework.main.data.model.ConditionDto

interface LoanApiService {

    @GET("/loans/conditions")
    suspend fun getConditions(): ConditionDto

    @GET("/loans/all")
    suspend fun getUserLoans(): List<LoanResponseDto>
}