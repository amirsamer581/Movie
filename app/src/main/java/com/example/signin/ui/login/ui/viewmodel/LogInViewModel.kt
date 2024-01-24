package com.example.signin.ui.login.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signin.domain.model.UserEntity
import com.example.signin.ui.login.domain.usecase.LogInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(private val loginUseCase: LogInUseCase) : ViewModel() {

    private val _loggedInUserStateFlow = MutableStateFlow<UserEntity?>(null)
    val loggedInUserStateFlow: StateFlow<UserEntity?> get() = _loggedInUserStateFlow


    private val _loggedInUserSharedFlow = MutableSharedFlow<UserEntity>()
    val loggedInUserSharedFlow: SharedFlow<UserEntity?> get() = _loggedInUserSharedFlow


    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase.execute(email, password).collect { user->
                if (user != null) {
                    _loggedInUserSharedFlow.emit(user)
                }
                _loggedInUserStateFlow.value = user
            }
        }
    }
}