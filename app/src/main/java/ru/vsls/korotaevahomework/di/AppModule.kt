package ru.vsls.korotaevahomework.di

import dagger.Binds
import dagger.Module
import ru.vsls.korotaevahomework.common.AppRouter
import ru.vsls.navigation.ActivityHolder
import ru.vsls.navigation.Router

@Module(includes = [FeatureModule::class])
interface AppModule {

    @Binds
    fun bindRouter(appRouter: AppRouter): Router

    @Binds
    fun bindActivityHolder(appRouter: AppRouter): ActivityHolder
}