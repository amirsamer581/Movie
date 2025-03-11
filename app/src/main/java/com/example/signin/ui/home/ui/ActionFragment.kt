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

/**
 * ActionFragment
 *
 * This Fragment displays a list of action movies fetched from the [MovieViewModel].
 * It utilizes a RecyclerView to present the movies and observes changes to the
 * movie data through the ViewModel's `movies` LiveData.
 *
 * Features:
 * - Displays a list of action movies.
 * - Uses a RecyclerView for efficient movie list rendering.
 * - Fetches movie data from [MovieViewModel].
 * - Updates the UI when new movie data is available.
 * - Uses Hilt for dependency injection.
 */
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
        setupRecyclerView()
        setupObservers()

    }
    private fun setupObservers(){
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.movies.collect { movies ->
                actionMovieAdapter = MoviesAdapter(movies)
                binding.actionMoviesRecyclerView.adapter = actionMovieAdapter
            }
        }
    }

    private fun setupRecyclerView() {
        binding.actionMoviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = actionMovieAdapter
        }
    }
}