package com.example.signin.di

import android.content.Context
import com.example.signin.data.local.UserDatabase
import com.example.signin.data.repository.UserRepositoryImpl
import com.example.signin.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * # UserDatabaseModule
 *
 * This Dagger Hilt module provides dependencies related to the user database.
 * It is installed in the [SingletonComponent], making the provided dependencies available
 * throughout the application's lifetime.
 *
 * **Responsibilities:**
 *
 * 1.  **Provides UserDatabase instance:**
 *     -   Provides a singleton instance of the [UserDatabase] using the application context.
 *     -   Utilizes the `UserDatabase.getInstance(context)` method to ensure a single database instance.
 * 2.  **Provides UserRepository instance:**
 *     -   Provides a singleton instance of the [UserRepository].
 *     -   Takes the [UserDatabase] as a dependency and injects it into the [UserRepositoryImpl] constructor.
 *     -   Retrieves the [UserDao] from the database to provide to the repository.
 *
 * **Annotations:**
 *
 * -   `@Module`: Indicates that this class is a Dagger module.
 * -   `@InstallIn(SingletonComponent::class)`: Specifies that this module is installed in the SingletonComponent,
 *     meaning the provided dependencies are available globally.
 * -   `@Provides`:  Marks methods that provide instances of dependencies.
 * -   `@Singleton`: Indicates that the provided instances should be singletons.
 * -   `@ApplicationContext`: Specifies that the application context should be injected into the `provideUserDatabase` method.
 *
 * **Usage:**
 *
 * Classes that require a [UserDatabase] or [UserRepository] instance can use constructor injection to
 * receive the provided dependencies. Hilt will automatically manage the dependency graph and provide the correct instances.*/
@Module
@InstallIn(SingletonComponent::class)
object UserDatabaseModule {

        @Provides
        @Singleton
        fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase {
                return UserDatabase.getInstance(context)
        }

        @Provides
        @Singleton
        fun provideUserRepository(database: UserDatabase) : UserRepository{
                return  UserRepositoryImpl(database.userDao())
        }

}