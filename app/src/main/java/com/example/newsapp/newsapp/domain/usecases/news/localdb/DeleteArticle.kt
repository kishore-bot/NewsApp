package com.example.newsapp.newsapp.domain.usecases.news.localdb

import com.example.newsapp.newsapp.data.local.NewsDao
import com.example.newsapp.newsapp.domain.model.Article
import com.example.newsapp.newsapp.domain.repository.NewsRepository

class DeleteArticle(

    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article){
        newsRepository.delete(article)
    }
}