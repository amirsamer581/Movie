package com.example.signin.ui.home.data

import com.example.signin.ui.home.data.local.MovieDao
import com.example.signin.ui.home.domain.Movie
import com.example.signin.ui.home.domain.MovieApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository(
    private val movieApiService: MovieApiService,
    private val movieDao: MovieDao
) {


    suspend fun getMovies(): Flow<List<Movie>> = flow {
        val localMovies = movieDao.getAllMovies()
        if (localMovies.isNotEmpty()) {
            emit(localMovies.map { movie ->
                Movie(
                    id = movie.id,
                    movie = movie.movie,
                    rating = movie.rating,
                    image = movie.image,
                    imdb_url = movie.imdb_url
                )
            })
        } else {
            val remoteMovies = movieApiService.getMovies()
            val movieEntities = remoteMovies.map { movie ->
                Movie(
                    id = movie.id,
                    movie = movie.movie,
                    rating = movie.rating,
                    image = movie.image,
                    imdb_url = movie.imdb_url
                )
            }
            movieDao.insertAll(movieEntities)
            emit(remoteMovies)
        }
    }
}