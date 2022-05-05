package com.aelfattah.ahmed.currency.core.di

import com.aelfattah.ahmed.currency.data.repository.MainRepositoryImpl
import com.aelfattah.ahmed.currency.domain.usecase.GetCurrenciesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class UseCasesModule {
    @Provides
    fun getCurrencies(repository: MainRepositoryImpl) = GetCurrenciesUseCase(repository = repository)
}