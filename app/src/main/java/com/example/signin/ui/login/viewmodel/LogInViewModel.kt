package com.example.signin.ui.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signin.data.UserRepository
import com.example.signin.domain.UserEntity
import kotlinx.coroutines.launch

class LogInViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _loggedInUser = MutableLiveData<UserEntity?>()
    val loggedInUser: LiveData<UserEntity?> get() = _loggedInUser
    fun login(email: String, password: String) {
        viewModelScope.launch {
            userRepository.getUserByEmailAndPassword(email, password).observeForever { user->
                _loggedInUser.value = user
            }
            }
        }
    }