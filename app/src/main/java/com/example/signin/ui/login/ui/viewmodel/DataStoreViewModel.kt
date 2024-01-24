//package com.example.signin.ui.login.ui.viewmodel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.signin.constants.SignInConstant.KEY_PASSWORD_EMAIL
//import com.example.signin.constants.SignInConstant.KEY_USER_EMAIL
//import com.example.signin.ui.login.domain.repository.DataStoreRepository
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.runBlocking
//import javax.inject.Inject
//
//@HiltViewModel
//class DataStoreViewModel @Inject constructor(
//    private val dataStoreRepository: DataStoreRepository
//) : ViewModel() {
//
//    fun saveEmail(value: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            dataStoreRepository.saveEmailDataToDataStore(KEY_USER_EMAIL, value)
//        }
//    }
//    fun getEmail(): String? = runBlocking {
//        dataStoreRepository.loadEmailDataFromDataStore(KEY_USER_EMAIL)
//    }
//
//
//    fun savePassword(value: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            dataStoreRepository.savePasswordDataToDataStore(KEY_PASSWORD_EMAIL, value)
//        }
//    }
//    fun getPassword(): String? = runBlocking {
//        dataStoreRepository.loadPasswordDataFromDataStore(KEY_PASSWORD_EMAIL)
//    }
//}

package com.example.signin.ui.login.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signin.ui.login.domain.usecase.DataStoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val dataStoreUseCase: DataStoreUseCase
) : ViewModel() {

    fun saveEmail(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreUseCase.saveEmail( value)
        }
    }
    suspend fun getEmail(): String? {//= runBlocking {
        return dataStoreUseCase.getEmail()
    }


    fun savePassword(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreUseCase.savePassword(value)
        }
    }
    fun getPassword(): String? = runBlocking {
        dataStoreUseCase.getPassword()
    }
}