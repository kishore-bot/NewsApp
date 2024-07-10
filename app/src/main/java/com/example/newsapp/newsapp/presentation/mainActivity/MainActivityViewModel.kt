package com.example.newsapp.newsapp.presentation.mainActivity


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.newsapp.domain.usecases.appEntry.AppEntryUseCases
import com.example.newsapp.newsapp.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    appEntryUseCases: AppEntryUseCases
) : ViewModel() {

     var splashCondition by mutableStateOf(true)
        private set


    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set


    init {
        appEntryUseCases.readAppEntry().onEach {
            startDestination = if (it.length ==2) {
                Route.NewsNavigation.route
            } else {
                Route.AppStartNavigation.route
            }
            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}