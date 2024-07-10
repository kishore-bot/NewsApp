package com.example.newsapp.newsapp.presentation.category


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.newsapp.domain.model.Article
import com.example.newsapp.newsapp.presentation.Dimens
import com.example.newsapp.newsapp.presentation.category.components.CategoryList
import com.example.newsapp.newsapp.presentation.common.AppTopSide
import com.example.newsapp.newsapp.presentation.common.ArticlesList


@Composable
fun CategoryScreen(
    state: CategoryState,
    event: (CategoryEvent) -> Unit,
    navigateToDetails: (Article) -> Unit,
    navigateToSearch: () -> Unit,

    ) {
    var selected by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(Unit) { // Fetch data on app start
        fetchCategory(selected, event)
    }
    Column(
        modifier = Modifier
            .padding(
                top = Dimens.MediumPadding1,
            )
            .statusBarsPadding()
    ) {
        AppTopSide (onClick = {navigateToSearch()})

        LazyRow() {
            items(categories.size) {
                CategoryList(category = categories[it], isSelected = it == selected) {
                    if (selected != it) {
                        selected = it
                        fetchCategory(selected, event)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

        state.articles?.let { it ->
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(article = articles, onClick = { navigateToDetails(it) })
        }
    }

}

private fun fetchCategory(
    selected: Int,
    event: (CategoryEvent) -> Unit,
) {
    event(CategoryEvent.SelectCategory(categories[selected]))
    event(CategoryEvent.GetCategory)

}

val categories = listOf(
    "business", "entertainment", "general", "health", "science", "sports", "technology"
)