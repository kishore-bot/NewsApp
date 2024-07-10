package com.example.newsapp.newsapp.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.newsapp.domain.model.Article
import com.example.newsapp.newsapp.presentation.Dimens.MediumPadding1
import com.example.newsapp.newsapp.presentation.common.ArticlesList
import com.example.newsapp.newsapp.presentation.search.components.SearchBar

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit,
    navigateUp: () -> Unit,
) {

    Column(
        modifier = Modifier
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp),

            ) {

            IconButton(onClick = { navigateUp() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 10.dp)
            ) {
                SearchBar(text = state.searchQuery,
                    readOnly = false,
                    onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
                    onSearch = {
                        event(SearchEvent.SearchNews)
                    })
            }

        }
        Spacer(modifier = Modifier.height(MediumPadding1))
        state.articles?.let { it ->
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(article = articles, onClick = { navigateToDetails(it) })
        }
    }
}