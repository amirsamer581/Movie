package com.example.signin.data

import com.example.signin.data.models.UserEntity

class UserRepository(private val userDao: UserDao) {
    suspend fun insertUser(user: UserEntity) = userDao.insertUser(user)

    suspend fun getUserByEmailAndPassword(email: String, password: String): UserEntity? = userDao.getUserByEmailAndPassword(email, password)
//    //TODO susoend
}