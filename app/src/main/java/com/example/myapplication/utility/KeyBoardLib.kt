package com.example.myapplication.utility

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.facebook.FacebookSdkNotInitializedException

class KeyBoardLib : AppCompatActivity(), View.OnClickListener {

    lateinit var b1 : Button
    lateinit var b2 : Button
    lateinit var b3 : Button
    lateinit var b4 : Button
    lateinit var b5 : Button
    lateinit var b6 : Button
    lateinit var b7 : Button
    lateinit var b8 : Button
    lateinit var b9 : Button

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.keyboard_lib_demo)

        b1 = findViewById(R.id.button2)
        b2 = findViewById(R.id.button3)
        b3 = findViewById(R.id.button4)
        b4 = findViewById(R.id.button5)
        b5 = findViewById(R.id.button6)
        b6 = findViewById(R.id.button7)
        b7 = findViewById(R.id.button8)
        b8 = findViewById(R.id.button9)
        b9 = findViewById(R.id.button10)

        b1.setOnClickListener(this)
        b2.setOnClickListener(this)
        b3.setOnClickListener(this)
        b4.setOnClickListener(this)
        b5.setOnClickListener(this)
        b6.setOnClickListener(this)
        b7.setOnClickListener(this)
        b8.setOnClickListener(this)
        b9.setOnClickListener(this)



    }
    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.button2 -> {
                
            }
            R.id.button3 -> {

            }
            R.id.button4 -> {

            }
            R.id.button5 -> {

            }
            R.id.button6 -> {

            }
            R.id.button7 -> {

            }
            R.id.button8 -> {

            }
            R.id.button9 -> {

            }
            R.id.button10 -> {

            }

        }

    }

}