package ru.vsls.details.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.vsls.details.data.DetailsApiService
import ru.vsls.details.data.DetailsRepositoryImpl
import ru.vsls.details.domain.DetailsRepository

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