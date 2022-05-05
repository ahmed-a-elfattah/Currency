package com.aelfattah.ahmed.currency.data.sources.remote

import com.aelfattah.ahmed.currency.data.sources.remote.dto.SymbolsDto
import retrofit2.http.GET

interface FixerApi {
    companion object{
        const val BASE_URL="https://api.apilayer.com/fixer/"
    }
    @GET("symbols")
    suspend fun getSymbols():SymbolsDto
}