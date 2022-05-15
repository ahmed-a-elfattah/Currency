package com.aelfattah.ahmed.currency.core.di

import com.aelfattah.ahmed.currency.domain.usecase.ConvertCurrencyUseCase
import com.aelfattah.ahmed.currency.domain.usecase.GetCurrenciesUseCase
import com.aelfattah.ahmed.currency.ui.feature.convertCurrency.ConvertCurrencyViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class ViewModelsModule {

    @Provides
    fun getConvertCurrencyViewModel(
        getCurrenciesUseCase: GetCurrenciesUseCase,
        convertCurrencyUseCase: ConvertCurrencyUseCase
    ) =
        ConvertCurrencyViewModel(
            getCurrenciesUseCase = getCurrenciesUseCase,
            convertCurrencyUseCase = convertCurrencyUseCase
        )
}