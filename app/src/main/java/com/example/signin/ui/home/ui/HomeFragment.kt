package com.example.signin.ui.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.signin.R
import com.example.signin.databinding.FragmentHomeBinding
import com.example.signin.ui.home.ui.adapter.MoviePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
                0 -> tab.text = "Action Movie"
                1 -> tab.text = "Comedy Movie"
                2 -> tab.text = "Adventure Movie"
            }
        }.attach()

        binding.btnLogOut.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_homeFragment_to_loginFragment2)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
    }
}