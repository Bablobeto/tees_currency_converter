package com.example.teescurrencyconverter.retrofit2.model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teescurrencyconverter.retrofit2.RetrofitClient
import com.example.teescurrencyconverter.retrofit2.data.ExchangeRate
import kotlinx.coroutines.launch

class RetrofitViewModel : ViewModel() {
    val exchangeData = MutableLiveData<ExchangeRate>()
    val targetExchangeRate = MutableLiveData<Double>()

    init {
        viewModelScope.launch {
            fetchExchangeRates()
        }
    }

    suspend fun fetchExchangeRates(
        baseCurrency: String = "USD",
        targetCurrency: String = "GBP"
    ) {
        try {
            val news = RetrofitClient.exchangeRateApiService.getNews(baseCurrency = baseCurrency)
            exchangeData.value = news

            Log.d("Source exchange currency", "Fetching rate for: $baseCurrency")
            Log.d("Target exchange rate ", targetCurrency + " Rate: " + news.data[targetCurrency])

            targetExchangeRate.value = news.data[targetCurrency];
        } catch (e: Exception) {
            // Handle potential errors here (e.g., network errors, API errors)
            Log.e("RetrofitViewModel exchange", "Error fetching exchange rates", e)
            // You might want to set an error state in your LiveData or display an error message to the user
        }
    }

}