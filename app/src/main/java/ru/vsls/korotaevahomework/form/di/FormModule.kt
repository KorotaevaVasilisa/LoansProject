package ru.vsls.korotaevahomework.form.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.vsls.korotaevahomework.form.data.FormApiService
import ru.vsls.korotaevahomework.form.data.FormRepositoryImpl
import ru.vsls.korotaevahomework.form.domain.FormRepository

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