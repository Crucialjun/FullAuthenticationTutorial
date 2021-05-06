package com.crucialtech.fullauthenticationtutorial

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(fm: FragmentManager,behavior : Int ,private val numberOfTabs: Int)
    : FragmentPagerAdapter(fm, behavior) {


    override fun getCount(): Int {
        return numberOfTabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> {
                LoginFragment()
            }
            1 -> {
                SignUpFragment()
            }
            else -> return SignUpFragment()
        }
    }
}