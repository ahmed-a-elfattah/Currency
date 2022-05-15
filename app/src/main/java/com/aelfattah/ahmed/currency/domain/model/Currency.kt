package com.aelfattah.ahmed.currency.domain.model

data class Currency(
    var code: String,
    var name: String,
) {
    override fun toString() = code
}
