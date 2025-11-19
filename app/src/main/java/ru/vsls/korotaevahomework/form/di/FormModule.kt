package ru.vsls.korotaevahomework.form.di

import dagger.Module
import dagger.Provides
import ru.vsls.korotaevahomework.form.FormFragment

@Module
interface FormModule {

    companion object {
//            @Provides
//            fun provideEnterApiService(retrofit: Retrofit): EnterApiService =
//                retrofit.create(EnterApiService::class.java)

        @Provides
        fun providePercent(fragment: FormFragment): Double =
            fragment.requireArguments().getDouble(FormFragment.ARG_PERCENT)
    }

}