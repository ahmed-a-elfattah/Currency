package com.aelfattah.ahmed.currency.domain.repository

import com.aelfattah.ahmed.currency.data.sources.remote.dto.ConversionDto
import com.aelfattah.ahmed.currency.data.sources.remote.dto.SymbolsDto

interface MainRepository {
    suspend fun getCurrencies(): SymbolsDto

    suspend fun getCurrencyConversion(from: String, to: String, amount: Float) : ConversionDto

}