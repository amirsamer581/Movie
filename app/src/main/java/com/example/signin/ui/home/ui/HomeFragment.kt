package com.example.signin.ui.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.signin.R
import com.example.signin.constants.KeyConstant.ACTION_MOVIE
import com.example.signin.constants.KeyConstant.ADVENTURE_MOVIE
import com.example.signin.constants.KeyConstant.COMEDY_MOVIE
import com.example.signin.constants.KeyConstant.DRAMA_MOVIE
import com.example.signin.databinding.FragmentHomeBinding
import com.example.signin.ui.home.ui.adapter.MoviePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

/**
 * The HomeFragment class is responsible for displaying the main screen of the application.
 * It uses a ViewPager2 and TabLayout to showcase different movie categories.
 *
 * This fragment provides the following functionalities:
 *  - Displays movie categories using tabs (Action, Comedy, Adventure).
 *  - Navigates to the LoginFragment when the logout button is clicked.
 *  - Navigates to the ImportObject fragment when the import button is clicked.
 *  - Manages the MoviePagerAdapter for the ViewPager2.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var moviePagerAdapter: MoviePagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()

        moviePagerAdapter = MoviePagerAdapter(this)
        binding.viewPager2.adapter = moviePagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = ACTION_MOVIE
                1 -> tab.text = COMEDY_MOVIE
                2 -> tab.text = ADVENTURE_MOVIE
                3 -> tab.text = DRAMA_MOVIE
            }
        }.attach()

    }

    private fun setupClickListeners(){
        binding.btnLogOut.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }
        binding.btnImport.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_homeFragment_to_importObject)
        }
    }
}