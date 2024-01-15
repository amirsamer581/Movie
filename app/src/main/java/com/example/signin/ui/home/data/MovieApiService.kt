package com.example.signin.ui.home.data

import com.example.signin.ui.home.domain.Movie
import retrofit2.http.GET

interface MovieApiService {

    @GET("movies")
    suspend fun getMovies(): List<Movie>
}
