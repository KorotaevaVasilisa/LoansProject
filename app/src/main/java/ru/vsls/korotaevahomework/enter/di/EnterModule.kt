package ru.vsls.korotaevahomework.enter.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import ru.vsls.korotaevahomework.di.ViewModelKey
import ru.vsls.korotaevahomework.enter.data.EnterApiService
import ru.vsls.korotaevahomework.enter.data.EnterRepositoryImpl
import ru.vsls.korotaevahomework.enter.domain.EnterRepository
import ru.vsls.korotaevahomework.enter.presentation.EnterViewModel

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