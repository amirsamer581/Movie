package com.example.signin.ui.login.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signin.ui.login.domain.usecase.DataStoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


// @HiltViewModel will make models to be
// created using Hilt's model factory
// @Inject annotation used to inject all
// dependencies to view model class
@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val dataStoreUseCase: DataStoreUseCase
) : ViewModel() {

    private val _emailData = MutableStateFlow<String?>(null)
    val emailData: StateFlow<String?> get() = _emailData

    private val _passwordData = MutableStateFlow<String?>(null)
    val passwordData: StateFlow<String?> get() = _passwordData

    init {
        getEmail()
        getPassword()
    }

    fun saveEmail(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreUseCase.saveEmail(value)
        }
    }

    fun savePassword(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreUseCase.savePassword(value)
        }
    }

    private fun getEmail(){
        viewModelScope.launch(Dispatchers.IO){
            dataStoreUseCase.getEmail().collect{ email ->
                _emailData.value = email
            }
        }
    }

    private fun getPassword(){
        viewModelScope.launch(Dispatchers.IO){
            dataStoreUseCase.getPassword().collect{ password ->
                _passwordData.value = password
            }
        }
    }
}