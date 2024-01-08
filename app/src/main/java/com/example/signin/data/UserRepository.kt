package com.example.signin.data


import com.example.signin.data.local.UserDao
import com.example.signin.domain.UserEntity
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    suspend fun insertUser(user: UserEntity) = userDao.insertUser(user)
    fun getUserByEmailAndPassword(email: String, password: String): Flow<UserEntity?> = userDao.getUserByEmailAndPassword(email, password)
}