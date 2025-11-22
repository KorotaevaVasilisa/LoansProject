package ru.vsls.korotaevahomework.di

import dagger.Binds
import dagger.Module
import ru.vsls.korotaevahomework.common.navigation.AppRouter
import ru.vsls.korotaevahomework.details.di.DetailsModule
import ru.vsls.korotaevahomework.history.di.HistoryModule
import ru.vsls.navigation.ActivityHolder
import ru.vsls.navigation.Router

@Module(includes = [FeatureModule::class, HistoryModule::class, DetailsModule::class])
interface AppModule {

    @Binds
    fun bindRouter(appRouter: AppRouter): Router

    @Binds
    fun bindActivityHolder(appRouter: AppRouter): ActivityHolder
}