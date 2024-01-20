package com.example.signin.ui.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.signin.databinding.FragmentAdventureBinding
import com.example.signin.ui.home.ui.adapter.MoviesAdapter
import com.example.signin.ui.home.ui.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AdventureFragment : Fragment() {
    private lateinit var binding: FragmentAdventureBinding
    private lateinit var adventureMovieAdapter: MoviesAdapter
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdventureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adventureMovieAdapter = MoviesAdapter(emptyList())

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
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