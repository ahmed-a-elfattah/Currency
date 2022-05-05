package com.aelfattah.ahmed.currency.core.di

import android.content.Context
import com.aelfattah.ahmed.currency.BuildConfig
import com.aelfattah.ahmed.currency.data.sources.remote.FixerApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().serializeNulls().setLenient().create()
    }

    @Provides
    fun provideInterceptor(): Interceptor {
        return Interceptor {
            val newHttpUrl = it.request().url.newBuilder()
                .addQueryParameter(
                    "apikey",
                    BuildConfig.fixer_api_key
                )
                .build()
            val request = it.request()
                .newBuilder()
                .url(newHttpUrl)
                .build()
            it.proceed(request)
        }
    }

    @Provides
    fun provideOkHttpClient(
        @ApplicationContext appContext: Context,
        interceptor: Interceptor
    ): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(1000, TimeUnit.SECONDS)
            .readTimeout(1000, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .cache(Cache(appContext.cacheDir.absoluteFile, 1024 * 1024 * 30))//30Mb

        return httpBuilder
            .protocols(mutableListOf(Protocol.HTTP_1_1))
            .build()
    }

    @Provides
    fun provideRetrofitClient(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(FixerApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }
}