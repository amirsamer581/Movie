package com.example.signin.ui.home.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.signin.R
import com.example.signin.databinding.ItemMovieBinding
import com.example.signin.ui.home.domain.model.Movie
import com.example.signin.constants.SignInConstant.MOVIE_DETAIL

class MoviesAdapter(private var movies: List<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.apply {//apply it super helpful to modify objects to use without creating variable
                when (movie.id) {
                  in  1..10 -> {
                      val imageUrl = getMovieImageUrl(movie.id!!)
                      val movieDetail = Movie(
                          id = movie.id,
                          movie = movie.movie,
                          rating = movie.rating,
                          image = imageUrl,
                          imdb_url = movie.imdb_url
                      )
                      loadImage(imageUrl)
                      setClickListener(movieDetail)
                  }
                }
            }
        }

        private fun loadImage(imageUrl: String) {
            Glide.with(binding.root)
                .load(imageUrl)
                .into(binding.movieImageView)
        }

        private fun setClickListener(movie: Movie) {
            binding.movieImageView.setOnClickListener {
                val movieDetail = Bundle().apply {
                    putParcelable(MOVIE_DETAIL, movie)
                }
                binding.root.findNavController().navigate(R.id.action_homeFragment_to_movieDetailFragment, movieDetail)
            }
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
}