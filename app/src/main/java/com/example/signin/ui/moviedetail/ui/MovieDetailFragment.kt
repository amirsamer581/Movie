package com.example.signin.ui.moviedetail.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.signin.R
import com.example.signin.constants.KeyConstant.MOVIE_DETAIL
import com.example.signin.databinding.FragmentMovieDetailBinding
import com.example.signin.ui.home.domain.model.Movie

/**
 * [MovieDetailFragment] displays the detailed information of a selected movie.
 *
 * This fragment retrieves movie data from arguments, sets up the UI with the movie's details,
 * and handles interactions like opening the movie's IMDb page.
 */
class MovieDetailFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailBinding
    private lateinit var movie: Movie
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setUpMovieDetail()
    }

    private fun setupClickListeners() {
        binding.urlLink1.setOnClickListener {
            openUrlPage(movie)
        }
    }

    private fun openUrlPage(movie: Movie) {
        //Intent.ACTION_VIEW this action is used to open a web page browser or another app to view content
        //Uri.parse(movie.imdb_url) it is use to converts the movie.imdb_url to Uri object
        val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.imdb_url))
        this.startActivity(urlIntent)
    }

    private fun setUpMovieDetail() {
        val movieDetail = getMovieDetail()
        binding.titleTextView1.text = getString(R.string.movie_title, movieDetail.movie)
        binding.ratingTextView1.text = getString(R.string.rating, movieDetail.rating)
        binding.urlLink1.text = getString(R.string.imdb_link, movieDetail.imdb_url)
        loadImage(movieDetail.image.toString())
    }

    private fun getMovieDetail(): Movie {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            movie = arguments?.getParcelable(MOVIE_DETAIL, Movie::class.java)!!
        }
        return movie
    }

    private fun loadImage(imageUrl: String) {
        val imageView = binding.movieImageView1
        Glide.with(this)
            .load(imageUrl)
            .into(imageView)
    }
}