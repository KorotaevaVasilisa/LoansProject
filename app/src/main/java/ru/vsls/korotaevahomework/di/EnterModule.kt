package ru.vsls.korotaevahomework.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import ru.vsls.korotaevahomework.data.EnterApiService
import ru.vsls.korotaevahomework.data.EnterRepositoryImpl
import ru.vsls.korotaevahomework.domain.EnterRepository
import ru.vsls.korotaevahomework.presentation.EnterViewModel

@Module
interface EnterModule {
    companion object {
        @Provides
        fun provideEnterApiService(retrofit: Retrofit): EnterApiService =
            retrofit.create(EnterApiService::class.java)
    }

    @Binds
    fun bindEnterRepository(impl: EnterRepositoryImpl): EnterRepository

    @Binds
    @IntoMap
    @ViewModelKey(EnterViewModel::class)
    fun bindFormViewModel(viewModel: EnterViewModel): ViewModel
}