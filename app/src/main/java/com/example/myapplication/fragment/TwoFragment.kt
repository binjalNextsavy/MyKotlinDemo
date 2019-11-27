package com.example.myapplication.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.R


class TwoFragment : Fragment() {

    lateinit var mView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_two, container, false)

        mView.findViewById<Toolbar>(R.id.toolbar).title = "Two"
        (activity as AppCompatActivity).supportActionBar?.title = "Two"
        return mView
    }


}
