package ru.vsls.form.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.vsls.form.data.FormApiService
import ru.vsls.form.data.FormRepositoryImpl
import ru.vsls.form.domain.FormRepository

@Module
interface FormModule {

    companion object {
        @Provides
        fun provideEnterApiService(retrofit: Retrofit): FormApiService =
            retrofit.create(FormApiService::class.java)
    }

    @Binds
    fun bindFormRepository(impl: FormRepositoryImpl): FormRepository

}