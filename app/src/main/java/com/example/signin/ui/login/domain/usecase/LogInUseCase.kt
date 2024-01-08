package com.example.signin.ui.login.domain.usecase

import com.example.signin.data.UserRepository
import com.example.signin.domain.UserEntity
import kotlinx.coroutines.flow.Flow

class LogInUseCase(private val userRepository: UserRepository) {

    fun execute(email: String, password: String): Flow<UserEntity?> {
        return userRepository.getUserByEmailAndPassword(email, password)
    }
}