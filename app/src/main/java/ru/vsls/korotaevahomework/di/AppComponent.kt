package ru.vsls.korotaevahomework.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.vsls.korotaevahomework.MainActivity
import ru.vsls.korotaevahomework.enter.di.EnterComponent
import ru.vsls.korotaevahomework.form.FormFragment
import ru.vsls.korotaevahomework.form.di.FormModule
import ru.vsls.korotaevahomework.main.di.MainModule
import ru.vsls.korotaevahomework.main.presentation.MainFragment
import ru.vsls.shared.network.di.NetworkModule

@Component(modules = [NetworkModule::class, FeatureModule::class, MainModule::class, FormModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
    fun inject(formFragment: FormFragment)

    fun enterComponent(): EnterComponent

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}