package ru.vsls.korotaevahomework.common.di

import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@Module
interface NetworkModule {
    companion object {
        const val BASE_URL = "https://shift-courses-result.yc.ftc.ru/v2/api-docs/"

        @Provides
        fun provideJson(): Json = Json { ignoreUnknownKeys = true }

        @Provides
        fun provideJsonFactory(json: Json): Converter.Factory =
            json.asConverterFactory("application/json".toMediaType())

        @Provides
        fun provideLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor()

        @Provides
        fun provideOkHttpClient(
            loggingInterceptor: HttpLoggingInterceptor,
        ): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

        @Provides
        fun provideRetrofit(
            client: OkHttpClient,
            jsonConverterFactory: Converter.Factory,
        ): Retrofit =
            Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(jsonConverterFactory)
                .build()
    }
}