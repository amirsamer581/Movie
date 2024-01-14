package com.example.signin.ui.home.domain

import retrofit2.http.GET

interface MovieApiService {

    @GET("movies")
    suspend fun getMovies(): List<Movie>
}
