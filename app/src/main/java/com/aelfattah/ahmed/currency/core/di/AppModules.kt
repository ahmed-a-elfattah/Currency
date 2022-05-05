package com.aelfattah.ahmed.currency.core.di

import com.aelfattah.ahmed.currency.data.sources.remote.FixerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Provides
    fun providesFixerApi(retrofit: Retrofit): FixerApi = retrofit.create(FixerApi::class.java)
}