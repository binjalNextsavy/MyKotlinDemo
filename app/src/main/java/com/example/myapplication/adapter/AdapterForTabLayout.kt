package com.example.myapplication.adapter

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myapplication.fragment.OneFragment
import com.example.myapplication.fragment.TwoFragment
import com.example.myapplication.fragment.ThreeFragment

class AdapterForTabLayout(private val context: Activity, fm: FragmentManager, private var totalTabs: Int) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return OneFragment()
            }

            1 -> {
                return TwoFragment()
            }

            2 -> {
                return ThreeFragment()
            }
            else -> return OneFragment()
        }


    }

    override fun getCount(): Int {
            return totalTabs
    }


}