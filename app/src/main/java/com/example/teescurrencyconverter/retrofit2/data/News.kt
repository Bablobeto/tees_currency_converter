package com.example.teescurrencyconverter.retrofit2.data

import com.example.teescurrencyconverter.retrofit2.data.Article

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)