package com.example.signin.data.repository


import com.example.signin.data.local.UserDao
import com.example.signin.domain.model.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun insertUser(user: UserEntity) = userDao.insertUser(user)

    fun getUserByEmailAndPassword(email: String, password: String): Flow<UserEntity?> = userDao.getUserByEmailAndPassword(email, password)

}