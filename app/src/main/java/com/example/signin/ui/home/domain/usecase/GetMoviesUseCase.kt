package com.example.signin.ui.home.domain.usecase

import com.example.signin.ui.home.data.repository.MovieRepositoryImpl
import com.example.signin.ui.home.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val movieRepositoryImpl: MovieRepositoryImpl) {
    suspend operator fun invoke(): Flow<List<Movie>> = movieRepositoryImpl.getMovies()
}