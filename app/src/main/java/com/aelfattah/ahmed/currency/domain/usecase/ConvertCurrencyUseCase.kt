package com.aelfattah.ahmed.currency.domain.usecase

import com.aelfattah.ahmed.currency.core.common.DataState
import com.aelfattah.ahmed.currency.data.repository.MainRepositoryImpl
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class ConvertCurrencyUseCase @Inject constructor(private val repository: MainRepositoryImpl) {
    operator fun invoke(from: String, to: String, amount: Float) = flow {
        try {
            emit(DataState.Loading)
            val result= repository.getCurrencyConversion(from = from, to = to, amount = amount)
            emit(DataState.Success(result.result))
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