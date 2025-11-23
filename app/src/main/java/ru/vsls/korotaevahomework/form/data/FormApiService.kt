package ru.vsls.korotaevahomework.form.data

import retrofit2.http.Body
import retrofit2.http.POST
import ru.vsls.korotaevahomework.form.data.model.LoanRequestDto
import ru.vsls.shared.network.data.model.LoanResponseDto

interface FormApiService {
    @POST("/loans")
    suspend fun postLoanRequest(@Body request: LoanRequestDto): LoanResponseDto
}