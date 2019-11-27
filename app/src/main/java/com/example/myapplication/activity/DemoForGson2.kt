package com.example.myapplication.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.model.User
import com.example.myapplication.R
import com.google.gson.Gson

class DemoForGson2 : AppCompatActivity() {

    lateinit var name: TextView
    lateinit var address: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_for_gson2)

        name = findViewById(R.id.name)
        address = findViewById(R.id.addres)
        val gson = Gson()

        /*val user1 = User("Abc", "AAA")
        val json1 = gson.toJson(user1)*/


        val json = "{\"mName\" : \"Abc\" ,\"mAddress\" : \"AAA\"}"
        val user = gson.fromJson(json, User::class.java)

        name.text = user.mName
        address.text = user.mAddress




    }
}