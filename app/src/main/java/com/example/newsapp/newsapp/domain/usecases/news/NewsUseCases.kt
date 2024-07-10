package com.example.newsapp.newsapp.domain.usecases.news

import com.example.newsapp.newsapp.domain.usecases.news.localdb.DeleteArticle
import com.example.newsapp.newsapp.domain.usecases.news.localdb.SelectArticle
import com.example.newsapp.newsapp.domain.usecases.news.localdb.SelectArticles
import com.example.newsapp.newsapp.domain.usecases.news.localdb.UpsertArticle
import com.example.newsapp.newsapp.domain.usecases.news.remote.GetCategory
import com.example.newsapp.newsapp.domain.usecases.news.remote.GetNews
import com.example.newsapp.newsapp.domain.usecases.news.remote.GetSearchNews
import com.example.newsapp.newsapp.domain.usecases.news.remote.GetSources

data class NewsUseCases(
    val getNews: GetNews,
    val getSearchNews: GetSearchNews,
    val getSources: GetSources,
    val getCategory: GetCategory,

//  DB
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val selectArticles: SelectArticles,
    val selectArticle: SelectArticle

)
