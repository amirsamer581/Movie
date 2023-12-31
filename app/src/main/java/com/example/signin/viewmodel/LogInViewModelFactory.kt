package com.example.signin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.signin.data.UserRepository

class LogInViewModelFactory(private val userRepository: UserRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LogInViewModel::class.java)) {

            LogInViewModel(userRepository) as T
        } else{
            SignUpViewModel(userRepository) as T
        }
//        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

