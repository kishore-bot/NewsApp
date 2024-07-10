package com.example.newsapp.newsapp.data.remote


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.newsapp.data.remote.dto.NewsApi
import com.example.newsapp.newsapp.domain.model.SourcesDetails



class SourcesPagingSource(private val newsApi: NewsApi) : PagingSource<Int, SourcesDetails>() {

    private var totalNewsCount = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SourcesDetails> {
        val page = params.key ?: 1
        return try {
            val newsResponse = newsApi.getSources(page = page)
            totalNewsCount += newsResponse.sources.size
            val article = newsResponse.sources.distinctBy { it.name }
            LoadResult.Page(
                data = article,
                nextKey = if (totalNewsCount == 100) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }


    override fun getRefreshKey(state: PagingState<Int, SourcesDetails>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition)
        return anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }
}
