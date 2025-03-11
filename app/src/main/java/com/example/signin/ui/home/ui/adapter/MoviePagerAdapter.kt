package com.example.signin.ui.home.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.signin.ui.home.ui.ActionFragment
import com.example.signin.ui.home.ui.AdventureFragment
import com.example.signin.ui.home.ui.ComedyFragment

/**
 * [MoviePagerAdapter] is a [FragmentStateAdapter] that manages the fragments
 * for different movie genres displayed in a ViewPager2.
 *
 * It provides three fragments: [ActionFragment], [ComedyFragment], and [AdventureFragment],
 * representing different movie genres.
 *
 * @param fragment The parent fragment or activity hosting the ViewPager2.
 */
class MoviePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ActionFragment()
            1 -> ComedyFragment()
            2 -> AdventureFragment()
            3 -> ActionFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}