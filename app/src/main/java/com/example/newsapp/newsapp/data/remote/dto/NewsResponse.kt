package com.example.newsapp.newsapp.data.remote.dto

import com.example.newsapp.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)