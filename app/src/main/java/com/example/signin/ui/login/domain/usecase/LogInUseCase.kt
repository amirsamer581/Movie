package com.example.signin.ui.login.domain.usecase

import com.example.signin.domain.model.UserEntity
import com.example.signin.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogInUseCase @Inject constructor(private val userRepository: UserRepository) {//LogInUseCase to handle the result state Error or Loading

    fun execute(email: String, password: String): Flow<UserEntity?> {
        return userRepository.getUserByEmailAndPassword(email, password)
    }
}