package com.example.signin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signin.data.UserRepository
import com.example.signin.data.models.UserEntity
import kotlinx.coroutines.launch

class SignUpViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun registerUser(user: UserEntity) {
        viewModelScope.launch {
            userRepository.insertUser(user)
        }
    }
}