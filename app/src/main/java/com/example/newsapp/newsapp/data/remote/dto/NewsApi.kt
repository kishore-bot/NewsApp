package com.example.newsapp.newsapp.data.remote.dto

import com.example.newsapp.newsapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("page") page:Int,
        @Query("country") country: String,
        @Query("apiKey") apiKey:String=API_KEY

    ):NewsResponse

    @GET("top-headlines?country=us")
    suspend fun getCategoryWise(
        @Query("page") page:Int,
        @Query("category") category:String,
        @Query("apiKey") apiKey:String=API_KEY
    ):NewsResponse


    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse


    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query("page") page:Int,
        @Query("apiKey") apiKey:String=API_KEY

    ):NewsSources
}
