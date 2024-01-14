package com.example.signin.ui.home.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.signin.ui.home.ui.ActionFragment
import com.example.signin.ui.home.ui.AdventureFragment
import com.example.signin.ui.home.ui.ComedyFragment

class MoviePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ActionFragment()
            1 -> ComedyFragment()
            2 -> AdventureFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}