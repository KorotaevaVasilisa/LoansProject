package ru.vsls.enter.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import ru.vsls.enter.data.EnterApiService
import ru.vsls.enter.data.EnterRepositoryImpl
import ru.vsls.enter.domain.EnterRepository
import ru.vsls.enter.presentation.EnterViewModel
import ru.vsls.navigation.di.ViewModelKey

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
    fun bindEnterViewModel(viewModel: EnterViewModel): ViewModel
}