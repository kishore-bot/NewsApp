package com.example.newsapp.newsapp.presentation.category

import androidx.paging.PagingData
import com.example.newsapp.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class CategoryState(
    val category: String = "",
    val articles: Flow<PagingData<Article>>? = null
)