package com.aelfattah.ahmed.currency.core.di

import com.aelfattah.ahmed.currency.data.repository.MainRepositoryImpl
import com.aelfattah.ahmed.currency.data.sources.remote.FixerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    fun providesMainRepository(api: FixerApi) = MainRepositoryImpl(fixerApi = api)
}