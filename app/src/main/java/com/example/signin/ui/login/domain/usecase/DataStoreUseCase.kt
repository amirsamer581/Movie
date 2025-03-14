package com.example.signin.ui.login.domain.usecase

import com.example.signin.constants.KeyConstant.KEY_USER_EMAIL
import com.example.signin.constants.KeyConstant.KEY_USER_PASSWORD
import com.example.signin.ui.login.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository){

    suspend fun saveEmail(value: String) {
        dataStoreRepository.saveEmailDataToDataStore(KEY_USER_EMAIL, value)
    }

    suspend fun savePassword(value: String) {
        dataStoreRepository.savePasswordDataToDataStore(KEY_USER_PASSWORD, value)
    }

    fun getEmail(): Flow<String?> {
        return dataStoreRepository.loadEmailDataFromDataStore(KEY_USER_EMAIL)
    }

     fun getPassword(): Flow<String?>  {
        return dataStoreRepository.loadPasswordDataFromDataStore(KEY_USER_PASSWORD)
    }
}