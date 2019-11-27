package com.example.myapplication.activity


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

import com.example.myapplication.R
import com.example.myapplication.adapter.ListviewAdapter

class DemoCheckedTv : Fragment() {

    lateinit var mView : View
    lateinit var value: String

    lateinit var listView: ListView

    var superStar = arrayOf("ABC","DEF","GDS","RRR","JHJ")
    //
    // var superStarName : String[] = {"ABC","DEF","GDS","RRR","JHJ"}
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_demo_checked_tv, container, false)

        listView = mView.findViewById(R.id.lvchecked)

        var customAdapter : ListviewAdapter = ListviewAdapter(superStar,context)
        listView.adapter = customAdapter

        return mView
    }


}
