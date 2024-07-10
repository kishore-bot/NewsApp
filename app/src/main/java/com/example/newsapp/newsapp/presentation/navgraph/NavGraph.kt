package com.example.newsapp.newsapp.presentation.navgraph


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.newsapp.presentation.news_navigator.NewsNavigator
import com.example.newsapp.newsapp.presentation.onbording.OnBoardingScreen
import com.example.newsapp.newsapp.presentation.onbording.OnBoardingViewModel
import com.example.newsapp.newsapp.presentation.onbording.components.countries

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        navigation(
            route = Route.AppStartNavigation.route, startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(countries = countries, viewModel = viewModel)
            }

        }
        navigation(
            route = Route.NewsNavigation.route, startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(
                route = Route.NewsNavigatorScreen.route
            ) {
                NewsNavigator()
            }

        }
    }
}