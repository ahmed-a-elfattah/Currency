package com.aelfattah.ahmed.currency.data.sources.remote

import com.aelfattah.ahmed.currency.data.sources.remote.dto.ConversionDto
import com.aelfattah.ahmed.currency.data.sources.remote.dto.SymbolsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FixerApi {
    companion object {
        const val BASE_URL = "https://api.apilayer.com/fixer/"
    }

    @GET("symbols")
    suspend fun getSymbols(): SymbolsDto

    @GET("convert")
    suspend fun getCurrencyConversion(
        @Query("to") to: String,
        @Query("from") from: String,
        @Query("amount") amount: Float
    ): ConversionDto
}