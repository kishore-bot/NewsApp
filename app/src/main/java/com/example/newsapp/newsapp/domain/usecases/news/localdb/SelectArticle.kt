package com.example.newsapp.newsapp.domain.usecases.news.localdb

import com.example.newsapp.newsapp.data.local.NewsDao
import com.example.newsapp.newsapp.domain.model.Article
import com.example.newsapp.newsapp.domain.repository.NewsRepository

class SelectArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String):Article?{
        return newsRepository.selectArticle(url = url)
    }
}