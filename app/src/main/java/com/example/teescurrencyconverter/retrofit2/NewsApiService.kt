package com.example.teescurrencyconverter.retrofit2

import com.example.teescurrencyconverter.retrofit2.data.News
import retrofit2.http.GET

interface NewsApiService {
    @GET("top-headlines?country=gb&category=health&sortBy=publishedAt&apiKey=2ef3b1666cbd412481baa2eeb67b81d9")
    suspend fun getNews() : News
}