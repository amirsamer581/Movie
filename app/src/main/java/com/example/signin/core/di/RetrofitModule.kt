package com.example.signin.core.di

import com.example.signin.ui.home.data.remote.MovieApiService
import com.example.signin.constants.SignInConstant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
// the SingletonComponent lives as the application live and the ActivityComponent will live as long the activity screen live
object RetrofitModule {  //RetrofitModule

    //private const val BASE_URL = "https://dummyapi.online/api/"

     private val movieApiService: MovieApiService by lazy { //lazy will not be intilaized by the way it will be intilaized when we access the api
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }


    @Provides
    @Singleton //@Singleton means that if have tow repository it will create one instance of provideMovieApiService
    fun provideMovieApiService() : MovieApiService {
        return movieApiService
    }
}
