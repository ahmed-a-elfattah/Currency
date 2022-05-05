package com.aelfattah.ahmed.currency.core.common

sealed class DataState<T>(){
    object Loading:DataState<Nothing>()
    data class Success<T>(val data:T):DataState<T>()
    data class Error(val message:String):DataState<Nothing>()
}
