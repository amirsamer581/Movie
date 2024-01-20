package com.example.signin.app

import com.example.signin.data.UserRepository
import com.example.signin.data.local.AppDatabase

object DependencyInjectionContainer {
    private val userRepository: UserRepository by lazy {
        UserRepository(
            AppDatabase.getInstance(MyApplication.instance).userDao()
        )
    }

    fun provideUserRepository(): UserRepository = userRepository
}
