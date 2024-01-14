package com.example.signin.ui.moviedetail.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.signin.databinding.FragmentMovieDetailBinding
import com.example.signin.ui.home.domain.Movie

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
//        movie = arguments?.getParcelable("movie")!!
        movie = requireArguments().getParcelable("movie_detail")!!

        binding.titleTextView1.text = "Movie: ${movie.movie}"
        binding.ratingTextView1.text = "Rating: ${movie.rating.toString()}"
        binding.urlLink1.text = "IMDb: ${movie.imdb_url}"

        //loadImage(movie.image!!)
        val imageUrl =getMovieImageUrl(movie.id!!)
        loadImage(imageUrl)

    }

    private fun loadImage(imageUrl : String){
        val imageView = binding.movieImageView1
        Glide.with(requireView())
            .load(imageUrl)
            .into(imageView)
    }

    private fun getMovieImageUrl(movieId: Int): String {
        return when (movieId) {
            1 -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTWOC8jhumgA0tVtYGbL2crS8T_8F3-G1tCqw&usqp=CAU"
            2 -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9CUF1QWfbGbBLZSSdDx5Y_l5xxsvyy8E9gA&usqp=CAU"
            3 -> "https://upload.wikimedia.org/wikipedia/sco/8/8a/Dark_Knight.jpg"
            4 -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS8LVWio9-JBkIQ57DynRetVJuJb3wWOFcuMA&usqp=CAU"
            5 -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlD-Wb7pdSydine_mKDcFPN0etOIFe5AF-Fa7KvwWp11el0VFNIrYrB_MLS2QhZ4A6jnY&usqp=CAU"
            6 -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRZf3I5TltV1D5-3LgdeXw_698YSX8KsWBgAQ&usqp=CAU"
            7 -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQInR1QrBK9GGPKsr0de0bSptHS2Coj4A8h_A&usqp=CAU"
            8 -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAotAEq4_liYHEFm_zsJb_TwDXlHyxbZc8pg&usqp=CAU"
            9 -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRadcf9SwS3htYfdzDKi_2DLFAZHt-PFRQCNA&usqp=CAU"
            10 -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9rKeOvwqdPagk_h-Og7lVL7LNhVzgWoAFVw&usqp=CAU"
            else -> "Invalid image"
        }
    }

}