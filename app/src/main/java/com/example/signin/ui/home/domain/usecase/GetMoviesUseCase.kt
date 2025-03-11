package com.example.signin.ui.home.domain.usecase

import com.example.signin.ui.home.domain.model.Movie
import com.example.signin.ui.home.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): Flow<List<Movie>> = movieRepository.getMovies()
}