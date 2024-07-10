package com.example.newsapp.newsapp.presentation.news_navigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.newsapp.domain.model.Article
import com.example.newsapp.newsapp.presentation.bookmark.BookmarkScreen
import com.example.newsapp.newsapp.presentation.bookmark.BookmarkViewModel
import com.example.newsapp.newsapp.presentation.category.CategoryScreen
import com.example.newsapp.newsapp.presentation.category.CategoryViewModel
import com.example.newsapp.newsapp.presentation.details.DetailsEvent
import com.example.newsapp.newsapp.presentation.details.DetailsScreen
import com.example.newsapp.newsapp.presentation.details.DetailsViewModel
import com.example.newsapp.newsapp.presentation.home.HomeScreen
import com.example.newsapp.newsapp.presentation.home.HomeViewModel
import com.example.newsapp.newsapp.presentation.navgraph.Route
import com.example.newsapp.newsapp.presentation.news_navigator.components.BottomNavigationItem
import com.example.newsapp.newsapp.presentation.news_navigator.components.NewsBottomNavigation
import com.example.newsapp.newsapp.presentation.search.SearchScreen
import com.example.newsapp.newsapp.presentation.search.SearchViewModel
import com.example.newsapp.newsapp.presentation.source.SourceScreen
import com.example.newsapp.newsapp.presentation.source.SourcesViewModel

@Composable
fun NewsNavigator() {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(imageVectorIcon = Icons.Default.Home, text = "Home"),
            BottomNavigationItem(imageVectorIcon = Icons.Rounded.CheckCircle, text = "Category"),
            BottomNavigationItem(imageVectorIcon = Icons.Default.FavoriteBorder, text = "Bookmark"),
            BottomNavigationItem(imageVectorIcon = Icons.Default.Info, text = "Sources"),
        )
    }
    val navController = rememberNavController()

    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by remember {
        mutableIntStateOf(0)
    }
    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.CategoryScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            Route.SourceScreen.route -> 3
            else -> 0
        }
    }
    val isBottomBarVisible = remember(
        key1 = backStackState
    ) {
        backStackState?.destination?.route == Route.HomeScreen.route || backStackState?.destination?.route == Route.SourceScreen.route || backStackState?.destination?.route == Route.BookmarkScreen.route || backStackState?.destination?.route == Route.CategoryScreen.route
    }
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
            NewsBottomNavigation(
                items = bottomNavigationItems,
                selected = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateTo(
                            navController, Route.HomeScreen.route
                        )

                        1 -> navigateTo(
                            navController, Route.CategoryScreen.route
                        )

                        2 -> navigateTo(

                            navController, Route.BookmarkScreen.route
                        )

                        3 -> navigateTo(
                            navController, Route.SourceScreen.route
                        )
                    }

                })
        }
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(
                route = Route.HomeScreen.route
            ) {
                val viewModel: HomeViewModel = hiltViewModel()
                val article = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(articles = article, navigateToSearch = {
                    navigateTo(navController = navController, route = Route.SearchScreen.route)
                }, navigateToDetail = { article ->
                    navigateToDetails(navController, article)
                })
            }
            composable(
                route = Route.SearchScreen.route
            ) {
                val viewModel: SearchViewModel = hiltViewModel()
                SearchScreen(state = viewModel.state.value,
                    event = viewModel::onEvent,
                    navigateToDetails = {
                        navigateToDetails(navController, article = it)
                    }, navigateUp = {navController.navigateUp()})
            }
            composable(
                route = Route.DetailsScreen.route
            ) {
                val viewModel: DetailsViewModel = hiltViewModel()
                if (viewModel.sideEffect != null) {
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT)
                        .show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { it ->
                        DetailsScreen(article = it,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() })
                    }
            }
            composable(
                route = Route.BookmarkScreen.route
            ) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookmarkScreen(state = state, navigateToDetails = { article ->
                    navigateToDetails(navController = navController, article = article)
                })
            }
            composable(
                route = Route.SourceScreen.route
            ) {
                val viewModel: SourcesViewModel = hiltViewModel()
                val source = viewModel.news.collectAsLazyPagingItems()
                SourceScreen(sources = source)
            }

            composable(
                route = Route.CategoryScreen.route
            ) {
                val viewModel: CategoryViewModel = hiltViewModel()
                val state = viewModel.state.value
                val event = viewModel::onEvent
                CategoryScreen(state = state, event = event, navigateToDetails = { article ->
                    navigateToDetails(navController = navController, article = article)
                }, navigateToSearch = {
                    navigateTo(navController = navController, route = Route.SearchScreen.route)
                }
                )

            }
        }
    }
}


private fun navigateTo(navController: NavController, route: String) {
    navController.navigate(route = route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )


}























