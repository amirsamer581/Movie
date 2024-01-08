package com.example.signin.ui.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signin.domain.UserEntity
import com.example.signin.ui.signup.domain.usecase.SignUpUseCase
import kotlinx.coroutines.launch

class SignUpViewModel(private val signUpUseCase: SignUpUseCase) : ViewModel() {
    fun registerUser(user: UserEntity) {
        viewModelScope.launch {
            signUpUseCase.execute(user)
        }
    }
}