package com.example.signin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signin.data.UserEntity
import com.example.signin.data.UserRepository
import kotlinx.coroutines.launch


class AuthViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            val user = userRepository.getUserByEmailAndPassword(email, password)
            _loginResult.value = user != null
        }
    }
    fun registerUser(user: UserEntity) {
        viewModelScope.launch {
            userRepository.insertUser(user)
        }
    }
}