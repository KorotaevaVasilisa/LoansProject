package ru.vsls.korotaevahomework.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.vsls.korotaevahomework.common.navigation.AppRouter
import ru.vsls.korotaevahomework.history.di.HistoryModule
import ru.vsls.navigation.ActivityHolder
import ru.vsls.navigation.Router
import javax.inject.Singleton

@Module(includes = [FeatureModule::class, HistoryModule::class])
interface AppModule {

    @Binds
    fun bindRouter(appRouter: AppRouter): Router

    @Binds
    fun bindActivityHolder(appRouter: AppRouter): ActivityHolder
}