package com.aelfattah.ahmed.currency.ui.feature.convertCurrency

import android.util.Log
import androidx.lifecycle.*
import com.aelfattah.ahmed.currency.core.common.DataState
import com.aelfattah.ahmed.currency.domain.model.Currency
import com.aelfattah.ahmed.currency.domain.usecase.ConvertCurrencyUseCase
import com.aelfattah.ahmed.currency.domain.usecase.GetCurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ConvertCurrencyViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
) : ViewModel() {
    companion object {
        private const val TAG = "ConvertCurrencyViewMode"
    }

    private val _currencies: MutableLiveData<ArrayList<Currency>> = MutableLiveData()
    val currencies: LiveData<ArrayList<Currency>> = _currencies

    private val _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> = _errorMessage

    private val _fromCurrency: MutableLiveData<String> = MutableLiveData()
    val fromCurrency: LiveData<String> = _fromCurrency

    private val _toCurrency: MutableLiveData<String> = MutableLiveData()
    val toCurrency: LiveData<String> = _toCurrency

    private val _amount: MutableLiveData<Float> = MutableLiveData(1f)
    val amount: LiveData<Float> = _amount

    private val _resultAmount: MutableLiveData<Float> = MutableLiveData(1f)
    val resultAmount: LiveData<Float> = _resultAmount

    init {
        getCurrencies()
    }

    private fun getCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            getCurrenciesUseCase().collect {
                when (it) {
                    is DataState.Loading -> {
                        Log.e(TAG, "getCurrencies: loading")
                    }
                    is DataState.Error -> {
                        _errorMessage.postValue(it.message)
                    }
                    is DataState.Success -> {
                        _currencies.postValue(it.data)
                    }
                }
            }
        }
    }

    fun convertCurrency() {
        viewModelScope.launch(Dispatchers.IO) {
            convertCurrencyUseCase(
                from = fromCurrency.value!!,
                to = toCurrency.value!!,
                amount = amount.value!!
            ).collect {
                when (it) {
                    is DataState.Loading -> {
                        Log.e("TAG", "convertCurrency: loading")
                    }
                    is DataState.Error -> {
                        Log.e(TAG, "convertCurrency: Error ${it.message}")
                        _errorMessage.postValue(it.message)
                    }
                    is DataState.Success -> {
                        Log.e("TAG", "convertCurrency: Success ${it.data}")
                        withContext(Dispatchers.Main) {
                            _resultAmount.value = it.data
                        }
                    }
                }
            }
        }
    }

    fun changeFromCurrency(currency: String) {
        _fromCurrency.value = currency
        Log.e("TAG", "changeFromCurrency: $currency")
    }

    fun changeToCurrency(currency: String) {
        _toCurrency.value = currency
        Log.e("TAG", "changeToCurrency:$currency ")
    }
}