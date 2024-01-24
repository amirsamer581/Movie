package com.example.signin.ui.login.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.signin.constants.SignInConstant
import com.example.signin.ui.login.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val context: Context
) : DataStoreRepository {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = SignInConstant.USER_PREFERENCES)

    override suspend fun saveEmailDataToDataStore(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun savePasswordDataToDataStore(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun loadEmailDataFromDataStore(key: String): String? {
        val preferencesKey = stringPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey]
    }

    override suspend fun loadPasswordDataFromDataStore(key: String): String? {
        val preferencesKey = stringPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey]
    }
}