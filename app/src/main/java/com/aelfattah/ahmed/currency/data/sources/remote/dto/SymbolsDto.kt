package com.aelfattah.ahmed.currency.data.sources.remote.dto

import com.aelfattah.ahmed.currency.domain.model.Currency


data class SymbolsDto(
    val success: Boolean,
    val symbols: HashMap<String,String>
)

fun SymbolsDto.toCurrencies(): ArrayList<Currency> {
    val currenciesSymbols=ArrayList<Currency>()
    symbols.forEach {
        currenciesSymbols.add(Currency(
            code = it.key,
            name = it.value
        ))
    }
    return currenciesSymbols
}