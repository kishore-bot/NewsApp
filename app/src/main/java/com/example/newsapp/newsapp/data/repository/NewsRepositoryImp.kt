package com.example.newsapp.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.newsapp.data.local.NewsDao
import com.example.newsapp.newsapp.data.remote.CategoryPagingSource
import com.example.newsapp.newsapp.data.remote.NewsPagingSource
import com.example.newsapp.newsapp.data.remote.SearchPagingSource
import com.example.newsapp.newsapp.data.remote.SourcesPagingSource
import com.example.newsapp.newsapp.data.remote.dto.NewsApi
import com.example.newsapp.newsapp.domain.model.Article
import com.example.newsapp.newsapp.domain.model.SourcesDetails
import com.example.newsapp.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImp(
    private val newsApi:NewsApi,
    private val newsDao: NewsDao
):NewsRepository {
    override fun getNews(country: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    country = country
                )
            }
        ).flow
    }
override fun searchNews(searchQuery: String): Flow<PagingData<Article>> {
    return Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            SearchPagingSource(
                api = newsApi,
                searchQuery = searchQuery,
            )
        }
    ).flow
}

    override fun getCategory(category: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                CategoryPagingSource(
                    newsApi = newsApi,
                    category = category
                )
            }
        ).flow
    }
    override fun getSource(): Flow<PagingData<SourcesDetails>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SourcesPagingSource(
                    newsApi = newsApi,
                )
            }
        ).flow
    }






    override suspend fun upsertArticles(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun delete(article: Article) {
       newsDao.delete(article)
    }

    override  fun selectArticles(): Flow<List<Article>> {
        return newsDao.getArticles().onEach { it.reversed() }
    }

    override suspend fun selectArticle(url: String): Article? {
       return newsDao.getArticle(url=url)
    }


}