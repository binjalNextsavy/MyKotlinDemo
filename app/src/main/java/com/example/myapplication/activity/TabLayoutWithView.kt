package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.adapter.AdapterForTabLayout
import com.example.myapplication.R
import com.google.android.material.tabs.TabLayout

class TabLayoutWithView : AppCompatActivity() {

    /*var tabLayout : TabLayout? = null
    var viewPager : ViewPager? = null
    */

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout_with_view)

        tabLayout = findViewById(R.id.tablayout)
        viewPager = findViewById(R.id.viewPager)
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "TabLayout"

        tabLayout.addTab(tabLayout.newTab().setText("One"))
        tabLayout.addTab(tabLayout.newTab().setText("Two"))
        tabLayout.addTab(tabLayout.newTab().setText("Three"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = AdapterForTabLayout(this, supportFragmentManager, tabLayout.tabCount)

        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0 != null) {
                    viewPager!!.currentItem = p0.position
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }
        })

    }
}
