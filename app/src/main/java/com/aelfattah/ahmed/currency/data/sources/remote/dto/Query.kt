package com.aelfattah.ahmed.currency.data.sources.remote.dto

data class Query(
    val amount: Float,
    val from: String,
    val to: String
)