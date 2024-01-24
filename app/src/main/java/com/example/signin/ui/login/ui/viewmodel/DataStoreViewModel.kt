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
    suspend fun getEmail(): String? {
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