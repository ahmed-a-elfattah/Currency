package com.aelfattah.ahmed.currency.data.sources.remote.dto

data class ConversionDto(
    val date: String,
    val historical: String,
    val info: Info,
    val query: Query,
    val result: Float,
    val success: Boolean
)