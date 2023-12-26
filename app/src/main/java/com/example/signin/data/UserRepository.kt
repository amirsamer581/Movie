package com.example.signin.data


class UserRepository(private val userDao: UserDao) {
     fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

     fun getUserByEmailAndPassword(email: String, password: String): UserEntity? {
        return userDao.getUserByEmailAndPassword(email, password)
    }
}