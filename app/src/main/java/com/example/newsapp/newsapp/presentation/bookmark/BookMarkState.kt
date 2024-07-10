package com.example.newsapp.newsapp.presentation.bookmark

import com.example.newsapp.newsapp.domain.model.Article
import com.example.newsapp.newsapp.domain.usecases.news.localdb.SelectArticles

data class BookmarkState(
    val articles: List<Article> = emptyList()
)