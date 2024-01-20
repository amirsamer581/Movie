package com.example.signin.ui.login.domain.usecase

import com.example.signin.data.repository.UserRepository
import com.example.signin.domain.model.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogInUseCase @Inject constructor(private val userRepository: UserRepository) {

    fun execute(email: String, password: String): Flow<UserEntity?> {
        return userRepository.getUserByEmailAndPassword(email, password)
    }
}