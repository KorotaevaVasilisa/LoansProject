package ru.vsls.korotaevahomework.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.vsls.korotaevahomework.common.navigation.ActivityHolder
import ru.vsls.korotaevahomework.common.navigation.AppRouter
import ru.vsls.korotaevahomework.common.navigation.Router
import javax.inject.Singleton

@Module(includes = [FeatureModule::class])
interface AppModule {
    companion object {
        @Provides
        @Singleton
        fun provideAppRouter(): AppRouter {
            return AppRouter()
        }
    }

    @Binds
    fun bindRouter(appRouter: AppRouter): Router

    @Binds
    fun bindActivityHolder(appRouter: AppRouter): ActivityHolder
}