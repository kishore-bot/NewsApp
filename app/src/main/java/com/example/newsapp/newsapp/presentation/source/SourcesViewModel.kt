package com.example.newsapp.newsapp.presentation.source

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class SourcesViewModel @Inject constructor(
    newsUseCases: NewsUseCases,

) : ViewModel() {
    val news = newsUseCases.getSources().cachedIn(viewModelScope)

}
