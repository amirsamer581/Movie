package com.example.signin.ui.login.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.signin.constants.KeyConstant.USER_PREFERENCES
import com.example.signin.ui.login.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

/**
 * Implementation of the [DataStoreRepository] interface.
 *
 * This class handles the storage and retrieval of user data (email and password) using Android's DataStore.
 * It provides methods for saving and loading sensitive information securely.
 *
 * @property context The application context, required for accessing DataStore.
 */
class DataStoreRepositoryImpl @Inject constructor(
    private val context: Context
) : DataStoreRepository {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCES)

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

    override fun loadEmailDataFromDataStore(key: String): Flow<String?> {
        val preferencesKey = stringPreferencesKey(key)
        return context.dataStore.data.catch { exception ->
            if (exception is IOException) {
                Log.e("DataStoreRepository", "Error reading preferences(loadEmailDataFromDataStore)", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
                preferences[preferencesKey]
            }
    }

    override fun loadPasswordDataFromDataStore(key: String): Flow<String?> {
        val preferencesKey = stringPreferencesKey(key)
        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    // Log the exception
                    Log.e("DataStoreRepository", "Error reading preferences(loadPasswordDataFromDataStore)", exception)
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[preferencesKey]
            }
    }
}