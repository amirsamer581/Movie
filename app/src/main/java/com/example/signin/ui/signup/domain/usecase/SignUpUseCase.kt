package com.example.signin.ui.signup.domain.usecase

import com.example.signin.domain.model.UserEntity
import com.example.signin.domain.repository.UserRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(user: UserEntity) {
        userRepository.insertUser(user)
    }
}