package com.example.newsapp.newsapp.domain.usecases.news.localdb

import com.example.newsapp.newsapp.domain.model.Article
import com.example.newsapp.newsapp.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article){
        newsRepository.upsertArticles(article)
    }
}