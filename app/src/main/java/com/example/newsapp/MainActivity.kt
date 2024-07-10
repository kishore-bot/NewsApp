package com.example.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.newsapp.newsapp.data.local.NewsDao
import com.example.newsapp.newsapp.domain.model.Article
import com.example.newsapp.newsapp.domain.model.Source
import com.example.newsapp.newsapp.domain.usecases.appEntry.AppEntryUseCases
import com.example.newsapp.newsapp.presentation.mainActivity.MainActivityViewModel
import com.example.newsapp.newsapp.presentation.navgraph.NavGraph
import com.example.newsapp.newsapp.presentation.onbording.OnBoardingScreen
import com.example.newsapp.newsapp.presentation.onbording.OnBoardingViewModel
import com.example.newsapp.newsapp.presentation.onbording.components.countries
import com.example.newsapp.ui.theme.NewsAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val viewModel by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition
            }
        }
        setContent {
            NewsAppTheme {
                val isSystemInDarkTheme = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()
                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Transparent, darkIcons = !isSystemInDarkTheme
                    )
                }
            }
            Box(modifier = Modifier.fillMaxSize()) {
                val startDestination = viewModel.startDestination
                NavGraph(startDestination = startDestination)

            }
        }
    }
}
