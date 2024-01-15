package com.example.signin.ui.home.domain.usecase

import com.example.signin.ui.home.data.MovieRepository
import com.example.signin.ui.home.domain.Movie
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): Flow<List<Movie>> = movieRepository.getMovies()
}