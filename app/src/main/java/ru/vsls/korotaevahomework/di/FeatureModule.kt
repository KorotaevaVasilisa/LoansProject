package ru.vsls.korotaevahomework.di

import dagger.Module
import dagger.Provides
import ru.vsls.enter.di.DaggerEnterComponent
import ru.vsls.enter.di.EnterComponent
import ru.vsls.korotaevahomework.form.di.DaggerFormComponent
import ru.vsls.korotaevahomework.form.di.FormComponent
import ru.vsls.korotaevahomework.history.di.DaggerHistoryComponent
import ru.vsls.korotaevahomework.history.di.HistoryComponent
import ru.vsls.korotaevahomework.main.di.DaggerMainComponent
import ru.vsls.korotaevahomework.main.di.MainComponent
import ru.vsls.korotaevahomework.screens.di.DaggerScreensComponent
import ru.vsls.korotaevahomework.screens.di.ScreensComponent

@Module
class FeatureModule {
    @Provides
    fun provideEnterComponent(deps: EnterComponent.Deps): EnterComponent =
        DaggerEnterComponent.builder().deps(deps).build()

    @Provides
    fun provideMainComponent(deps: MainComponent.Deps): MainComponent =
        DaggerMainComponent.builder().deps(deps).build()

    @Provides
    fun provideFormComponent(deps: FormComponent.Deps): FormComponent =
        DaggerFormComponent.builder().deps(deps).build()

    @Provides
    fun provideHistoryComponent(deps: HistoryComponent.Deps): HistoryComponent =
        DaggerHistoryComponent.builder().deps(deps).build()

    @Provides
    fun provideScreensComponent(deps: ScreensComponent.Deps): ScreensComponent =
        DaggerScreensComponent.builder().deps(deps).build()
}