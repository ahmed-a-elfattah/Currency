package com.aelfattah.ahmed.currency.data.repository

import com.aelfattah.ahmed.currency.data.sources.remote.FixerApi
import com.aelfattah.ahmed.currency.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val fixerApi: FixerApi) : MainRepository {

    override suspend fun getCurrencies() = fixerApi.getSymbols()
}