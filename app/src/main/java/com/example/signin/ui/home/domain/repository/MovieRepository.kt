package com.example.signin.ui.home.domain.repository

import com.example.signin.ui.home.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(): Flow<List<Movie>>
}