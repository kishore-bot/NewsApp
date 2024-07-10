package com.example.newsapp.newsapp.domain.repository

import androidx.paging.PagingData
import com.example.newsapp.newsapp.domain.model.Article
import com.example.newsapp.newsapp.domain.model.SourcesDetails
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(country: String): Flow<PagingData<Article>>
    fun searchNews(searchQuery: String): Flow<PagingData<Article>>
    fun getCategory(category:String):  Flow<PagingData<Article>>
    fun getSource(): Flow<PagingData<SourcesDetails>>


    suspend fun upsertArticles(article: Article)
    suspend fun delete(article: Article)
    fun selectArticles(): Flow<List<Article>>
    suspend fun selectArticle(url: String): Article?
}