package com.example.newsapp.newsapp.domain.usecases.appEntry

import com.example.newsapp.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
private val localUserManager: LocalUserManager
){
     operator fun invoke(): Flow<String> {
        return localUserManager.readAppEntry()
    }
}