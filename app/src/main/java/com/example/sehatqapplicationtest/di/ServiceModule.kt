package com.example.sehatqapplicationtest.di

import com.example.sehatqapplicationtest.BuildConfig
import com.example.sehatqapplicationtest.api.ApiHelper
import com.example.sehatqapplicationtest.api.ApiHelperImpl
import com.example.sehatqapplicationtest.api.AppApiService
import com.example.sehatqapplicationtest.util.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by M Hafidh Abdul Aziz on 21/01/21.
 */

@Module
@InstallIn(ApplicationComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(AppConstants.BASE_URL)
            .client(okHttpClient)
    }

    @Singleton
    @Provides
    fun provideAppService(retrofit: Retrofit.Builder): AppApiService {
        return retrofit
            .build()
            .create(AppApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper
}