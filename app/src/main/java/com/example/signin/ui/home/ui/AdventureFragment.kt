package com.example.signin.ui.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.signin.databinding.FragmentAdventureBinding
import com.example.signin.ui.home.data.MovieRepository
import com.example.signin.ui.home.data.local.MovieDatabase
import com.example.signin.ui.home.data.RetrofitClient
import com.example.signin.ui.home.domain.usecase.GetMoviesUseCase
import com.example.signin.ui.home.ui.adapter.MoviesAdapter
import com.example.signin.ui.home.ui.viewmodel.MovieViewModel
import com.example.signin.ui.home.ui.viewmodel.factory.MovieViewModelFactory
import kotlinx.coroutines.launch

class AdventureFragment : Fragment() {
    private lateinit var binding: FragmentAdventureBinding
    private lateinit var adventureMovieAdapter: MoviesAdapter
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
        binding = FragmentAdventureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[MovieViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.movies.collect { movies ->
                adventureMovieAdapter = MoviesAdapter(movies)
                binding.adventureMoviesRecyclerView.adapter = adventureMovieAdapter
            }
        }

        viewModel.fetchData()
        setupRecyclerView()
    }
    private fun setupRecyclerView() {
        binding.adventureMoviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adventureMovieAdapter
        }
    }

}