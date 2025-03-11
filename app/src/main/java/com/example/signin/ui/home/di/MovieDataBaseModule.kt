package com.example.signin.ui.home.di

import android.content.Context
import com.example.signin.constants.KeyConstant.MOVIE_BASE_URL
import com.example.signin.ui.home.data.local.MovieDatabase
import com.example.signin.ui.home.data.remote.MovieApiService
import com.example.signin.ui.home.data.repository.MovieRepositoryImpl
import com.example.signin.ui.home.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * # MovieDataBaseModule
 *
 * This Dagger Hilt module provides dependencies related to the movie data layer,
 * including the [MovieDatabase], [MovieRepository], and underlying network service ([MovieApiService]).
 *
 * ## Functionality:
 *
 * 1.  **Provides Network Service:** Creates and provides a singleton instance of the [MovieApiService]
 *     using Retrofit. The API service is responsible for making network requests to the movie database.
 *     It uses the `MOVIE_BASE_URL` constant for the base URL of the API.
 * 2.  **Provides Database:** Creates and provides a singleton instance of the [MovieDatabase]
 *     using the provided application context. This database stores the movie data locally.
 * 3.  **Provides Repository:** Creates and provides a singleton instance of the [MovieRepository].
 *     This repository acts as a single point of access to the movie data, combining data
 *     from both the network and the local database.*/
@Module
@InstallIn(SingletonComponent::class)
// the SingletonComponent lives as the application live and the ActivityComponent will live as long the activity screen live
object MovieDataBaseModule {

    private val movieApiService: MovieApiService by lazy { //lazy will not be initialized by the way it will be initialized when we access the api
        Retrofit.Builder()
            .baseUrl(MOVIE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }

    @Provides // @Provides annotation means to till the dagger hilt this function provide dependency injection
    @Singleton // @Singleton means that if have tow repository it will create one instance of provideMovieApiService
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return MovieDatabase.getDatabase(context)
        //dependency inversion
    }

    @Provides
    @Singleton
    fun provideMovieRepository(database: MovieDatabase): MovieRepository {
        return MovieRepositoryImpl(movieApiService, database.movieDao())
    }

}