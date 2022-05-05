package com.aelfattah.ahmed.currency.domain.repository

import com.aelfattah.ahmed.currency.data.sources.remote.dto.SymbolsDto

interface MainRepository {
    suspend fun getCurrencies(): SymbolsDto
}