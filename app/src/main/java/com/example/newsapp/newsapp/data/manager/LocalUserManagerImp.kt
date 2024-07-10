package com.example.newsapp.newsapp.data.manager

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsapp.newsapp.domain.manager.LocalUserManager
import com.example.newsapp.newsapp.util.Constants
import com.example.newsapp.newsapp.util.Constants.USER_COUNTRY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImp(
    private val context: Context
):LocalUserManager  {


    override suspend fun saveAppEntry(countryCode: String) {
        context.dataStore.edit {settings->
            settings[PreferencesKeys.APP_ENTRY]=countryCode
        }
    }

    override fun readAppEntry(): Flow<String> {
        return context.dataStore.data.map {preferences->
            (if(preferences[PreferencesKeys.APP_ENTRY]=="" ) return@map " ".toString()
            else preferences[PreferencesKeys.APP_ENTRY]).toString()
        }
    }


}

private val Context.dataStore:DataStore<Preferences> by preferencesDataStore(name = USER_COUNTRY)

private  object PreferencesKeys{
    val APP_ENTRY= stringPreferencesKey(name = Constants.APP_ENTRY)
}