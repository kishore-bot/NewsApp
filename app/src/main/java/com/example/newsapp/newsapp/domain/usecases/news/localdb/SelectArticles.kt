package com.example.newsapp.newsapp.domain.usecases.news.localdb

import com.example.newsapp.newsapp.data.local.NewsDao
import com.example.newsapp.newsapp.domain.model.Article
import com.example.newsapp.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {
    operator fun invoke():Flow<List<Article>>{
        return newsRepository.selectArticles()
    }
}