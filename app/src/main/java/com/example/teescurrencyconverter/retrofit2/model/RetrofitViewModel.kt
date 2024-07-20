package com.example.teescurrencyconverter.retrofit2.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teescurrencyconverter.retrofit2.RetrofitClient
import com.example.teescurrencyconverter.retrofit2.data.News
import kotlinx.coroutines.launch

class RetrofitViewModel : ViewModel() {
    val harryPotterData = MutableLiveData<News>()

    init {
        viewModelScope.launch {
            getNews()
        }
    }

    private suspend fun getNews() {
        harryPotterData.value = RetrofitClient
            .newsApiService
            .getNews()
    }

}