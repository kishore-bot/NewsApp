package com.example.newsapp.newsapp.data.remote.dto

import com.example.newsapp.newsapp.domain.model.SourcesDetails

data class NewsSources(
    val sources: List<SourcesDetails>,
    val status: String
)