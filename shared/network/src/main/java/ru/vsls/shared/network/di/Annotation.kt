package ru.vsls.shared.network.di

import jakarta.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class TokenInterceptorNamed

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ErrorInterceptorNamed
