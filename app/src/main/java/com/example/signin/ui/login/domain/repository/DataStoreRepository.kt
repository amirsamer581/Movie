package com.example.signin.ui.login.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    suspend fun saveEmailDataToDataStore(key: String, value: String)

    suspend fun savePasswordDataToDataStore(key: String, value: String)

    fun loadEmailDataFromDataStore(key: String): Flow<String?>

    fun loadPasswordDataFromDataStore(key: String): Flow<String?>

}