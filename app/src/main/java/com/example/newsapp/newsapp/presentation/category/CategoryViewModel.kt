package com.example.newsapp.newsapp.presentation.category

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
   private val newsUseCases: NewsUseCases,
) : ViewModel() {

    private var _state = mutableStateOf(CategoryState())
    val state: State<CategoryState> = _state


    fun onEvent(event: CategoryEvent) {
        when (event) {
            is CategoryEvent.SelectCategory -> {
                _state.value = _state.value.copy(category = event.category)
            }

            is CategoryEvent.GetCategory -> {
                searchNews()
            }
        }
    }
    private fun searchNews() {
        val articles = newsUseCases.getCategory(
            category = _state.value.category
        ).cachedIn(viewModelScope)
        _state.value = _state.value.copy(articles = articles)
    }

}