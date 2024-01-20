package com.example.signin.ui.signup.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signin.domain.model.UserEntity
import com.example.signin.ui.signup.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {
    fun registerUser(user: UserEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            signUpUseCase.execute(user)
        }
    }
}