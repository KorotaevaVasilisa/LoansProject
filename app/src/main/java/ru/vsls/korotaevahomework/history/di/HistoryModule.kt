package ru.vsls.korotaevahomework.history.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import ru.vsls.korotaevahomework.history.data.HistoryApiService
import ru.vsls.korotaevahomework.history.data.HistoryRepositoryImpl
import ru.vsls.korotaevahomework.history.domain.HistoryRepository
import ru.vsls.korotaevahomework.history.presentation.HistoryViewModel
import ru.vsls.navigation.di.ViewModelKey

@Module
interface HistoryModule {
    companion object {
        @Provides
        fun provideHistoryApiService(retrofit: Retrofit): HistoryApiService =
            retrofit.create(HistoryApiService::class.java)
    }

    @Binds
    fun bindHistoryRepository(impl: HistoryRepositoryImpl): HistoryRepository

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    fun bindHistoryViewModel(viewModel: HistoryViewModel): ViewModel
}