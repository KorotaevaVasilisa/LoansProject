package ru.vsls.korotaevahomework.details.data

import retrofit2.http.GET
import retrofit2.http.Path
import ru.vsls.korotaevahomework.form.data.model.LoanResponseDto

interface DetailsApiService {
    @GET("/loans/{loanId}")
    suspend fun getLoan(@Path("loanId") loanId: Int): LoanResponseDto
}