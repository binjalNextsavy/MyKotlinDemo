package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.User
import com.google.android.material.bottomnavigation.BottomNavigationView

class CustomRecyclerview : AppCompatActivity() {
    lateinit var recyclerview: RecyclerView
    lateinit var bottomNavigation : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_recyclerview)

        recyclerview = findViewById(R.id.recyclerview)
        var toolbar = findViewById<Toolbar>(R.id.toolbar)

       /* recyclerview.setOnClickListener { Unit -> View.OnClickListener(
            Toast.makeText(this,"CLick",Toast.LENGTH_LONG).show()
        ) }*/

        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId){
                R.id.call -> {
                    //For custom toolbar
                    toolbar.title = "Call"
                    //for by default toolbar
                    (this as AppCompatActivity).supportActionBar?.title = "Call"
                    true
                }
                R.id.sms -> {
                    toolbar.title = "SMS"
                    (this as AppCompatActivity).supportActionBar?.title = "SMS"
                    true
                } else -> {
                super.onOptionsItemSelected(it)
            }
            }
        }


        bottomNavigation = findViewById(R.id.bottom)

        var recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(this)

        val users = ArrayList<User>()

        users.add(User("ABC", "ZZZZZZ"))
        users.add(User("XYZ", "ZZZZZZ"))
        users.add(User("PQR", "ZZZZZZ"))
        users.add(User("QWE", "ZZZZZZ"))
        users.add(User("ABC", "ZZZZZZ"))
        users.add(User("XYZ", "ZZZZZZ"))
        users.add(User("PQR", "ZZZZZZ"))
        users.add(User("QWE", "ZZZZZZ"))
        users.add(User("ABC", "ZZZZZZ"))
        users.add(User("XYZ", "ZZZZZZ"))
        users.add(User("PQR", "ZZZZZZ"))
        users.add(User("QWE", "ZZZZZZ"))

        //val adapter = AdapterForrecycler(users)
        //recyclerview.adapter = adapter

        //Hide/show bottom layout when scroll recyclerview
        /*recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 ||dy < 0)    // && csButtonLay.isShown()
                {
                    bottom.visibility = View.GONE
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    bottom.visibility = View.VISIBLE
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })*/


    }

}
