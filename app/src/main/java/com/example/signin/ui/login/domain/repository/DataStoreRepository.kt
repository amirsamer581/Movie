package com.example.signin.ui.login.domain.repository

interface DataStoreRepository {

    suspend fun saveEmailDataToDataStore(key: String, value: String)

    suspend fun savePasswordDataToDataStore(key: String, value: String)
    suspend fun loadEmailDataFromDataStore(key: String): String?
    suspend fun loadPasswordDataFromDataStore(key: String): String?

}