package com.example.simpleandroidapp.di


import com.example.simpleandroidapp.network.AppService
import com.example.simpleandroidapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun getBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient()
            : OkHttpClient {
        return OkHttpClient().newBuilder()
            .callTimeout(15, TimeUnit.SECONDS)
            .readTimeout(3, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        provideOkHttpClient: OkHttpClient,
        provGsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(provideOkHttpClient)
            .baseUrl(getBaseUrl())
            .addConverterFactory(provGsonConverterFactory)
            .build()

    }

    @Singleton
    @Provides
    fun appService(provideRetrofit: Retrofit): AppService {
        return provideRetrofit.create(AppService::class.java)
    }

}

