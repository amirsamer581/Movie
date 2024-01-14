package com.example.signin.ui.home.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signin.ui.home.domain.Movie
import com.example.signin.ui.home.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val getMoviesUseCase: GetMoviesUseCase) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> get() = _movies

    fun fetchData() {
        viewModelScope.launch {
            getMoviesUseCase.invoke().collect { moviesList ->
                _movies.value = moviesList
            }
        }
    }
}
