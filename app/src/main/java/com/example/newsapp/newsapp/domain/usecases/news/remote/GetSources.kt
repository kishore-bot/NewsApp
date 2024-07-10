package com.example.newsapp.newsapp.domain.usecases.news.remote

import androidx.paging.PagingData
import com.example.newsapp.newsapp.domain.model.SourcesDetails
import com.example.newsapp.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSources(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<PagingData<SourcesDetails>> {
        return newsRepository.getSource()
    }
}