package com.example.myapplication.firestore

import android.graphics.ColorSpace
import android.os.Bundle
import android.util.Log
import android.view.Display
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.AdapterForrecycler1
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore


import kotlinx.android.synthetic.main.activity_main.*

import com.google.firebase.firestore.QuerySnapshot


//https://stackoverflow.com/questions/51746587/display-data-from-firebase-firestore-to-recyclerview-with-kotlin
//https://stackoverflow.com/questions/58615496/is-there-a-way-to-update-the-firestorerecycleroptions-object-or-the-query-object

class DisplayListActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    var db = FirebaseFirestore.getInstance()
    lateinit var mAdapter : AdapterForrecycler1
     lateinit var mArrayList  : ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_list)

        mArrayList = ArrayList()


        recyclerView = findViewById(R.id.recycler_list)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //val query = db.collection("Document").orderBy("id")

        db.collection("Document")
            .get()
            .addOnSuccessListener{ result ->
                for (document in result){
                    Log.e("Document","${document.id} => ${document.data}}")
                    var mObject : ModelF = ModelF(document.id,"","")
                    var id = document.id

                   val desc =  document.getString("desc")
                    document.getString("id")
                    document.getString("title")

                    mArrayList.add(desc!!)

                }
            }.addOnFailureListener { e ->
                Log.e("Document","Exception $e")
            }

        mAdapter = AdapterForrecycler1(mArrayList)
        recyclerView.adapter = mAdapter
    }

}
