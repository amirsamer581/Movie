package com.example.signin.ui.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.signin.R
import com.example.signin.constants.SignInConstant.ACTION_MOVIE
import com.example.signin.constants.SignInConstant.ADVENTURE_MOVIE
import com.example.signin.constants.SignInConstant.COMEDY_MOVIE
import com.example.signin.databinding.FragmentHomeBinding
import com.example.signin.ui.home.ui.adapter.MoviePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

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

        moviePagerAdapter = MoviePagerAdapter(this)
        binding.viewPager2.adapter = moviePagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = ACTION_MOVIE
                1 -> tab.text = COMEDY_MOVIE
                2 -> tab.text = ADVENTURE_MOVIE
            }
        }.attach()

        binding.btnLogOut.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_homeFragment_to_loginFragment2)
        }
        //todo find a solution for the back button from the home fragment to the login fragment
    }

    override fun onDestroy() {
        super.onDestroy()
        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
    }
}