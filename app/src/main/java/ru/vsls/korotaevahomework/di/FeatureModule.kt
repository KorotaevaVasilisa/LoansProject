package ru.vsls.korotaevahomework.di

import dagger.Module
import dagger.Provides
import ru.vsls.korotaevahomework.enter.di.DaggerEnterComponent
import ru.vsls.korotaevahomework.enter.di.EnterComponent
import ru.vsls.korotaevahomework.main.di.DaggerMainComponent
import ru.vsls.korotaevahomework.main.di.MainComponent

@Module
class FeatureModule {
    @Provides
    fun provideEnterComponent(deps: EnterComponent.Deps): EnterComponent =
        DaggerEnterComponent.builder().deps(deps).build()

    @Provides
    fun provideMainComponent(deps: MainComponent.Deps): MainComponent =
        DaggerMainComponent.builder().deps(deps).build()
}