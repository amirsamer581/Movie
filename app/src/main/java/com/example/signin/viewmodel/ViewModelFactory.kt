package com.example.signin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.signin.data.UserRepository
import com.example.signin.ui.login.viewmodel.LogInViewModel
import com.example.signin.ui.signup.viewmodel.SignUpViewModel
class ViewModelFactory(private val userRepository: UserRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LogInViewModel::class.java)) {

            LogInViewModel(userRepository) as T
        } else{
            SignUpViewModel(userRepository) as T
        }
        @Suppress("UNREACHABLE_CODE")
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

