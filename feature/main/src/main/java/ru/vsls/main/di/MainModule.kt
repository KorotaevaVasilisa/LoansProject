package ru.vsls.main.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import ru.vsls.navigation.di.ViewModelKey
import ru.vsls.main.data.LoanApiService
import ru.vsls.main.data.LoanRepositoryImpl
import ru.vsls.main.domain.LoanRepository
import ru.vsls.main.presentation.MainViewModel

@Module
interface MainModule {
    companion object {
        @Provides
        fun provideLoanApiService(retrofit: Retrofit): LoanApiService =
            retrofit.create(LoanApiService::class.java)
    }

    @Binds
    fun bindLoanRepository(impl: LoanRepositoryImpl): LoanRepository

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}