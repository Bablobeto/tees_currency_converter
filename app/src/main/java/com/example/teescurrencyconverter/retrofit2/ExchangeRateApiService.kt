package com.example.teescurrencyconverter.retrofit2

import com.example.teescurrencyconverter.retrofit2.data.ExchangeRate
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRateApiService {
    @GET("latest")
    suspend fun getNews(
        @Query("apikey") apiKey: String ="fca_live_GdPoyosrKEIahSKj91I8BzwCYbKdmWVdiWct1ME9",
        @Query("base_currency") baseCurrency: String
    ): ExchangeRate
}