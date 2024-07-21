package com.example.teescurrencyconverter.retrofit2.data

import com.google.gson.annotations.SerializedName

data class ExchangeRate(
    val data: Map<String, Double>
)