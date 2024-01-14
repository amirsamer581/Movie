package com.example.signin.ui.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.signin.databinding.FragmentComedyBinding
import com.example.signin.ui.home.data.MovieRepository
import com.example.signin.ui.home.data.local.MovieDatabase
import com.example.signin.ui.home.domain.RetrofitClient
import com.example.signin.ui.home.domain.usecase.GetMoviesUseCase
import com.example.signin.ui.home.ui.adapter.MoviesAdapter
import com.example.signin.ui.home.ui.viewmodel.MovieViewModel
import com.example.signin.ui.home.ui.viewmodel.factory.MovieViewModelFactory
import kotlinx.coroutines.launch

class ComedyFragment : Fragment() {
    private lateinit var binding: FragmentComedyBinding
    private lateinit var comedyMovieAdapter: MoviesAdapter
    private lateinit var viewModel: MovieViewModel
    private val getMoviesUseCase by lazy {
        GetMoviesUseCase(
            movieRepository = MovieRepository(
                RetrofitClient.movieApiService,
                movieDao = MovieDatabase.getDatabase(requireContext()).movieDao()
            )
        ) }
    private val viewModelFactory by lazy {
        MovieViewModelFactory(getMoviesUseCase)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComedyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[MovieViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.movies.collect { movies ->
                comedyMovieAdapter = MoviesAdapter(movies)
                binding.comedyMoviesRecyclerView.adapter = comedyMovieAdapter
            }
        }

        viewModel.fetchData()
        setupRecyclerView()
    }
    private fun setupRecyclerView() {
        binding.comedyMoviesRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = comedyMovieAdapter
        }
    }

}