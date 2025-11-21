package ru.vsls.korotaevahomework.di

import dagger.Module
import dagger.Provides
import ru.vsls.korotaevahomework.enter.di.DaggerEnterComponent
import ru.vsls.korotaevahomework.enter.di.EnterComponent

@Module
class FeatureModule {
    @Provides
    fun provideEnterComponent(deps: EnterComponent.Deps): EnterComponent =
        DaggerEnterComponent.builder().deps(deps).build()
}