package com.crucialtech.fullauthenticationtutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val tabs = findViewById<TabLayout>(R.id.tabs)
        val pager = findViewById<ViewPager>(R.id.pager)
        val adapter = PagerAdapter(supportFragmentManager
            ,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            ,tabs.tabCount
        )

        pager.adapter = adapter

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                pager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

//        pager.addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(tabs) {
//
//        })

    }
}