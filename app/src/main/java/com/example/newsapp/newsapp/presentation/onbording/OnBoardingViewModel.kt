package com.example.newsapp.newsapp.presentation.onbording

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.newsapp.domain.usecases.appEntry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {
    private val _selectedCountryCode = MutableStateFlow<String?>(null)
    private val selectedCountryCode = _selectedCountryCode.asStateFlow()

    fun saveSelectedCountryCode(countryCode: String) {
        _selectedCountryCode.value = countryCode
    }
    fun saveAppEntry() {
        viewModelScope.launch {
            val countryCode = selectedCountryCode.value
            if (!countryCode.isNullOrBlank()) {
                withContext(Dispatchers.IO) {
                    appEntryUseCases.saveAppEntry(countryCode)
                }
            }
        }
    }
}




