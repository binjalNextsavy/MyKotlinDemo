package com.example.myapplication.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import com.example.myapplication.activity.DemoForCamera
import com.example.myapplication.R
import com.example.myapplication.activity.DemoCheckedTv
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_one.view.*


class
OneFragment : Fragment() {

    lateinit var mView: View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


            mView = inflater.inflate(R.layout.fragment_one, container, false)

            mView.findViewById<Toolbar>(R.id.toolbar).title = "One"

            (activity as AppCompatActivity).supportActionBar?.title = "One"


            mView.button.text = "Camera"
            mView.button.setOnClickListener {
                Snackbar.make(mView,"One Fragment",Snackbar.LENGTH_LONG).show()

                val intent = Intent(context,DemoForCamera::class.java)
                startActivity(intent)

            }




            return mView


    }


}
