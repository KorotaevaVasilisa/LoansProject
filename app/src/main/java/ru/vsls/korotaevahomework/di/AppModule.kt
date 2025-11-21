package ru.vsls.korotaevahomework.di

import dagger.Module
import dagger.Provides
import ru.vsls.korotaevahomework.common.navigation.AppRouter
import ru.vsls.korotaevahomework.common.navigation.Router
import javax.inject.Singleton

@Module(includes = [FeatureModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideRouter(): Router {
        return AppRouter()
    }
}