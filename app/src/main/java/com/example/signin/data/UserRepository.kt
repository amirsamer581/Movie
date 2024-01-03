package com.example.signin.data

import androidx.lifecycle.LiveData
import com.example.signin.data.local.UserDao
import com.example.signin.domain.UserEntity
class UserRepository(private val userDao: UserDao) {
    suspend fun insertUser(user: UserEntity) = userDao.insertUser(user)
    fun getUserByEmailAndPassword(email: String, password: String): LiveData<UserEntity?> = userDao.getUserByEmailAndPassword(email, password)
}