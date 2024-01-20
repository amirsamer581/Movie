package com.example.signin.ui.home.data.remote

import com.example.signin.ui.home.domain.model.Movie
import retrofit2.http.GET

interface MovieApiService {

    @GET("movies")
    suspend fun getMovies(): List<Movie>
    // using suspend because it should always run in a coroutine never in the main thread
}