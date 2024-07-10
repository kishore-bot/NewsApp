package com.example.newsapp.newsapp.domain.usecases.news.remote

import androidx.compose.runtime.State
import androidx.paging.PagingData
import com.example.newsapp.newsapp.domain.model.Article
import com.example.newsapp.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews (
    private val newsRepository: NewsRepository
) {
    operator fun invoke(country: String): Flow<PagingData<Article>> {
        return newsRepository.getNews(country = country)
    }
}