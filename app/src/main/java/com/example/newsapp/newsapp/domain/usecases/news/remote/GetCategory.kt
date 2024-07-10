package com.example.newsapp.newsapp.domain.usecases.news.remote

import androidx.paging.PagingData
import com.example.newsapp.newsapp.domain.model.Article
import com.example.newsapp.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetCategory (
    private val newsRepository: NewsRepository
) {
    operator fun invoke(category: String): Flow<PagingData<Article>> {
        return newsRepository.getCategory(category = category)
    }
}