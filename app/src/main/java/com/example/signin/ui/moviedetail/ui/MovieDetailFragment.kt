package com.example.signin.ui.moviedetail.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.signin.databinding.FragmentMovieDetailBinding
import com.example.signin.ui.home.domain.model.Movie
import com.example.signin.constants.SignInConstant.MOVIE_DETAIL

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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        @Suppress("DEPRECATION")
        movie = requireArguments().getParcelable(MOVIE_DETAIL)!!

        binding.titleTextView1.text = "Movie: ${movie.movie}"
        binding.ratingTextView1.text = "Rating: ${movie.rating.toString()}"
        binding.urlLink1.text = "IMDb: ${movie.imdb_url}"
        loadImage(movie.image.toString())

        binding.urlLink1.setOnClickListener {
            val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.imdb_url))
            //Intent.ACTION_VIEW this action is used to open a web page browser or another app to view content
            //Uri.parse(movie.imdb_url) it is use to converts the movie.imdb_url to Uri object
            binding.root.context.startActivity(urlIntent)
        }

    }

    private fun loadImage(imageUrl : String) {
        val imageView = binding.movieImageView1
        Glide.with(requireView())
            .load(imageUrl)
            .into(imageView)
    }

}