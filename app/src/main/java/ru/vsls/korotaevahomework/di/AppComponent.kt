package ru.vsls.korotaevahomework.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.vsls.enter.di.EnterComponent
import ru.vsls.korotaevahomework.MainActivity
import ru.vsls.korotaevahomework.form.di.FormComponent
import ru.vsls.korotaevahomework.main.di.MainComponent
import ru.vsls.shared.network.di.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun formComponent(): FormComponent
    fun enterComponent(): EnterComponent
    fun mainComponent(): MainComponent

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}