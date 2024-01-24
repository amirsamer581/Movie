package com.example.signin.ui.login.domain.usecase

import com.example.signin.constants.SignInConstant.KEY_USER_EMAIL
import com.example.signin.constants.SignInConstant.KEY_USER_PASSWORD
import com.example.signin.ui.login.domain.repository.DataStoreRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DataStoreUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository){

    suspend fun saveEmail(value: String) {
        dataStoreRepository.saveEmailDataToDataStore(KEY_USER_EMAIL, value)
        getEmail()
    }
    suspend fun getEmail(): String? {
        return dataStoreRepository.loadEmailDataFromDataStore(KEY_USER_EMAIL)
    }


    suspend fun savePassword(value: String) {
        dataStoreRepository.savePasswordDataToDataStore(KEY_USER_PASSWORD, value)
        getPassword()
    }
    fun getPassword(): String? = runBlocking {
        dataStoreRepository.loadPasswordDataFromDataStore(KEY_USER_PASSWORD)
    }
}