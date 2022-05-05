package com.aelfattah.ahmed.currency.domain.usecase

import com.aelfattah.ahmed.currency.core.common.DataState
import com.aelfattah.ahmed.currency.data.repository.MainRepositoryImpl
import com.aelfattah.ahmed.currency.data.sources.remote.dto.toCurrencies
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetCurrenciesUseCase @Inject constructor(private val repository: MainRepositoryImpl) {

    operator fun invoke()= flow {
        try {
            emit(DataState.Loading)
            val currencies=repository.getCurrencies().toCurrencies()
            emit(DataState.Success(currencies))
        }catch (e:HttpException){
            emit(DataState.Error(e.message!!))
        }
    }
}