package com.example.newsapp.newsapp.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun saveAppEntry(countryCode:String)
    fun readAppEntry(): Flow<String>
}