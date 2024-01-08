package com.example.signin.ui.signup.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.signin.ui.signup.domain.usecase.SignUpUseCase
import com.example.signin.ui.signup.viewmodel.SignUpViewModel

class SignUpViewModelFactory(private val signUpUseCase: SignUpUseCase) : ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignUpViewModel::class.java) ->
                SignUpViewModel(signUpUseCase) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}