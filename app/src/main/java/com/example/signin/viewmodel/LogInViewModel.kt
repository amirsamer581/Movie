package com.example.signin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signin.data.UserRepository
import com.example.signin.data.models.UserEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LogInViewModel(private val userRepository: UserRepository) : ViewModel() {
private val _loggedInUser = MutableStateFlow<UserEntity?>(null)
    val loggedInUser: StateFlow<UserEntity?> get() = _loggedInUser

    fun login(email: String, password: String) {
        viewModelScope.launch {
            userRepository.getUserByEmailAndPassword(email, password).collect{user ->
                _loggedInUser.value = user
            }
        }
    }
}