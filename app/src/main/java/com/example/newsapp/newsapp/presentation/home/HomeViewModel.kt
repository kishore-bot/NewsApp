package com.example.newsapp.newsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.newsapp.domain.usecases.appEntry.AppEntryUseCases
import com.example.newsapp.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    newsUseCases: NewsUseCases,
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    private val _code = MutableStateFlow<String?>("in") // Default value is "in"
    val code: StateFlow<String?> = _code

    init {
        viewModelScope.launch {
            appEntryUseCases.readAppEntry().collect { value ->
                _code.value = value
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val news = code.flatMapLatest { country ->
        newsUseCases.getNews(country ?: "us").cachedIn(viewModelScope)
    }
}
