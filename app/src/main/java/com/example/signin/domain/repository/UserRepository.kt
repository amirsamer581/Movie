package com.example.signin.domain.repository

import com.example.signin.domain.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun insertUser(user: UserEntity)

    fun getUserByEmailAndPassword(email: String, password: String): Flow<UserEntity?>
}