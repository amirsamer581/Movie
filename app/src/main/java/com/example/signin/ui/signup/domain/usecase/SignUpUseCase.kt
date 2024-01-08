package com.example.signin.ui.signup.domain.usecase

import com.example.signin.data.UserRepository
import com.example.signin.domain.UserEntity

class SignUpUseCase(private val userRepository: UserRepository) {

    suspend fun execute(user: UserEntity) {
        userRepository.insertUser(user)
    }
}