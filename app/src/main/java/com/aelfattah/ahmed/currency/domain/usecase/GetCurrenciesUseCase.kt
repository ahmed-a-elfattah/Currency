package com.aelfattah.ahmed.currency.domain.usecase

import com.aelfattah.ahmed.currency.core.common.DataState
import com.aelfattah.ahmed.currency.data.repository.MainRepositoryImpl
import com.aelfattah.ahmed.currency.data.sources.remote.dto.toCurrencies
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCurrenciesUseCase @Inject constructor(private val repository: MainRepositoryImpl) {

    operator fun invoke()= flow {
        try {
            emit(DataState.Loading)
            val currencies=repository.getCurrencies().toCurrencies()
            emit(DataState.Success(currencies))
        }catch (e:HttpException){
            when(e.code()){
                429 ->{
                    emit(DataState.Error("Free Plan for https://apilayer.com/subscriptions exceeded"))
                }
                else ->{
                    emit(DataState.Error(e.message!!))
                }
            }
        }catch (e:IOException){
            emit(DataState.Error("unknown error"))
        }
    }
}