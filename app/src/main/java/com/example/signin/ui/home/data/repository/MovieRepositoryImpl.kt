package com.example.signin.ui.home.data.repository

import android.util.Log
import com.example.signin.ui.home.data.local.MovieDao
import com.example.signin.ui.home.data.remote.MovieApiService
import com.example.signin.ui.home.domain.model.Movie
import com.example.signin.ui.home.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

/**
 * Implementation of the [MovieRepository] interface.
 *
 * This class is responsible for fetching movie data from either a local database (using [MovieDao])
 * or a remote API (using [MovieApiService]). It handles potential exceptions during data fetching
 * and provides the data as a [Flow].
 *
 * @property movieApiService The service used to fetch movie data from the remote API.
 * @property movieDao The DAO used to interact with the local movie database.
 */
class MovieRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val movieDao: MovieDao
) : MovieRepository {

    /**
     * Fetches a list of movies.
     *
     * This function first attempts to retrieve movies from the local database. If the local database
     * is empty, it fetches movies from the remote API, stores them in the local database, and then
     * emits the remote movies.
     *
     * @return A [Flow] emitting a list of [Movie] objects.
     */
    override suspend fun getMovies(): Flow<List<Movie>> = flow {
        Log.d("MovieRepository", "Fetching movies...")
        val localMovies = movieDao.getAllMovies()
        if (localMovies.isNotEmpty()) {
            Log.d("MovieRepository", "Movies found locally: ${localMovies.size} movies")
            emit(localMovies.map { movieEntity ->
                Movie(
                    id = movieEntity.id,
                    movie = movieEntity.movie,
                    rating = movieEntity.rating,
                    image = movieEntity.image,
                    imdb_url = movieEntity.imdb_url
                )
            })
        } else {
            Log.d("MovieRepository", "No movies found locally, fetching from remote...")
            try {
                val remoteMovies = movieApiService.getMovies()
                Log.d("MovieRepository", "Movies fetched from remote: ${remoteMovies.size} movies...")
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
            } catch (e: IOException) {
                Log.e("MovieRepository", "Network error while fetching movies", e)
                emit(emptyList())
            } catch (e: Exception) {
                Log.e("MovieRepository", "Unexpected error while fetching movies", e)
                emit(emptyList())
            }
        }
    }.catch { exception ->
        Log.e("MovieRepository", "Error in flow", exception)
        // Handle any exceptions that occur in the flow itself
        emit(emptyList())
    }.flowOn(Dispatchers.IO)
}