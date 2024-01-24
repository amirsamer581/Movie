package com.example.signin.ui.login.domain.usecase

import com.example.signin.data.repository.UserRepositoryImpl
import com.example.signin.domain.model.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogInUseCase @Inject constructor(private val userRepositoryImpl: UserRepositoryImpl) {//LogInUseCase to handle the result state Error or Loading

    fun execute(email: String, password: String): Flow<UserEntity?> {
        return userRepositoryImpl.getUserByEmailAndPassword(email, password)
    }

}