package com.example.signin.ui.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.signin.databinding.FragmentActionBinding
import com.example.signin.ui.home.ui.adapter.MoviesAdapter
import com.example.signin.ui.home.ui.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ActionFragment : Fragment() {
    private lateinit var binding: FragmentActionBinding
    private lateinit var actionMovieAdapter: MoviesAdapter
    private val viewModel: MovieViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActionBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        actionMovieAdapter = MoviesAdapter(emptyList())

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            //the lifecycleScope stop when move to other screen
            viewModel.movies.collect { movies ->
                actionMovieAdapter = MoviesAdapter(movies)
                binding.actionMoviesRecyclerView.adapter = actionMovieAdapter
            }
        }

        viewModel.fetchData()
        setupRecyclerView()

    }
    private fun setupRecyclerView() {
        binding.actionMoviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = actionMovieAdapter
        }
    }
}