package com.example.signin.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signin.domain.UserEntity
import com.example.signin.ui.login.domain.usecase.LogInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LogInViewModel(private val loginUseCase: LogInUseCase) : ViewModel() {

    private val _loggedInUser = MutableStateFlow<UserEntity?>(null)
    val loggedInUser: StateFlow<UserEntity?> get() = _loggedInUser

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase.execute(email, password).collect { user->
                _loggedInUser.value = user
            }//TODO viewModelScope.launch  &&  lifecycle aware
        }
    }
}