package com.example.signin.data.repository


import com.example.signin.data.local.UserDao
import com.example.signin.domain.model.UserEntity
import com.example.signin.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {

    override suspend fun insertUser(user: UserEntity) = userDao.insertUser(user)

    override fun getUserByEmailAndPassword(email: String, password: String): Flow<UserEntity?> = userDao.getUserByEmailAndPassword(email, password)

}