package com.example.newsapp.newsapp.domain.usecases.appEntry

import com.example.newsapp.newsapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(countryCode: String) {
        localUserManager.saveAppEntry(countryCode = countryCode)
    }
}