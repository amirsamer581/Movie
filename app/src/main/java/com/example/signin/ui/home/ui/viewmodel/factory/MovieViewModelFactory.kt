package com.example.signin.ui.home.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.signin.ui.home.domain.usecase.GetMoviesUseCase
import com.example.signin.ui.home.ui.viewmodel.MovieViewModel

class MovieViewModelFactory(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel(getMoviesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

