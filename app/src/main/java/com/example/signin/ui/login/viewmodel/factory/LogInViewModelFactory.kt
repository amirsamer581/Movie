package com.example.signin.ui.login.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.signin.ui.login.domain.usecase.LogInUseCase
import com.example.signin.ui.login.viewmodel.LogInViewModel

class LogInViewModelFactory(private val logInUseCase: LogInUseCase) :
    ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LogInViewModel::class.java) ->
                LogInViewModel(logInUseCase) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}