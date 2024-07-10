package com.example.newsapp.newsapp.presentation.category

sealed class CategoryEvent{

    data class SelectCategory(val category: String) : CategoryEvent()

    data object GetCategory : CategoryEvent()
}
