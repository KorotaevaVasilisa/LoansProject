package ru.vsls.korotaevahomework.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.vsls.korotaevahomework.MainActivity
import ru.vsls.korotaevahomework.common.di.NetworkModule
import ru.vsls.korotaevahomework.enter.di.EnterModule
import ru.vsls.korotaevahomework.enter.presentation.EnterFragment

@Component(modules = [NetworkModule::class, EnterModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(enterFragment: EnterFragment)


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}