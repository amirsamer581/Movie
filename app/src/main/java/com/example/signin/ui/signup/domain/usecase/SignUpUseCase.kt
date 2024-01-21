package com.example.signin.ui.signup.domain.usecase

import com.example.signin.data.repository.UserRepositoryImpl
import com.example.signin.domain.model.UserEntity
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val userRepositoryImpl: UserRepositoryImpl) {

    suspend fun execute(user: UserEntity) {
        userRepositoryImpl.insertUser(user)
    }
}