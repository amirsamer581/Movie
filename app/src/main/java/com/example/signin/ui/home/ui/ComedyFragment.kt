package com.example.signin.ui.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.signin.databinding.FragmentComedyBinding
import com.example.signin.ui.home.ui.adapter.MoviesAdapter
import com.example.signin.ui.home.ui.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ComedyFragment
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
class ComedyFragment : Fragment() {
    private lateinit var binding: FragmentComedyBinding
    private lateinit var comedyMovieAdapter: MoviesAdapter
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComedyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        comedyMovieAdapter = MoviesAdapter(emptyList())
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.movies.collect { movies ->
                comedyMovieAdapter = MoviesAdapter(movies)
                binding.comedyMoviesRecyclerView.adapter = comedyMovieAdapter
            }
        }
    }
    private fun setupRecyclerView() {
        binding.comedyMoviesRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = comedyMovieAdapter
        }
    }

}