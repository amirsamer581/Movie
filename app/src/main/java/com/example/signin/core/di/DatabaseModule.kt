package com.example.signin.core.di

import android.content.Context
import com.example.signin.data.local.UserDao
import com.example.signin.data.local.UserDatabase
import com.example.signin.ui.login.data.repository.DataStoreRepositoryImpl
import com.example.signin.data.repository.UserRepositoryImpl
import com.example.signin.ui.login.domain.repository.DataStoreRepository
import com.example.signin.domain.repository.UserRepository
import com.example.signin.ui.home.data.local.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

        @Provides
        @Singleton
        fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
            return MovieDatabase.getDatabase(context)
        }

        @Provides
        @Singleton
        fun provideMovieDao(database: MovieDatabase) = database.movieDao()

        @Provides
        @Singleton
        fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase {
                return UserDatabase.getInstance(context)
        }

        @Provides
        @Singleton
        fun provideUserDao(database: UserDatabase) = database.userDao()


        @Provides
        @Singleton
        fun provideUserRepository(userDao: UserDao) : UserRepository{
                return  UserRepositoryImpl(userDao)
        }

        @Provides
        @Singleton
        fun provideDataStoreRepository(@ApplicationContext context: Context) : DataStoreRepository {
                return  DataStoreRepositoryImpl(context)
        }

}

