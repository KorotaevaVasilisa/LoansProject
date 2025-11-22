package ru.vsls.korotaevahomework.details.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.vsls.korotaevahomework.details.data.DetailsApiService
import ru.vsls.korotaevahomework.details.data.DetailsRepositoryImpl
import ru.vsls.korotaevahomework.details.domain.DetailsRepository

@Module
interface DetailsModule {
    companion object {
        @Provides
        fun provideDetailApiService(retrofit: Retrofit): DetailsApiService =
            retrofit.create(DetailsApiService::class.java)
    }

    @Binds
    fun bindDetailsRepository(impl: DetailsRepositoryImpl): DetailsRepository
}