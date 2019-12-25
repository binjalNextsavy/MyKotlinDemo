package com.example.myapplication.firestore

import android.content.Intent
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.R
import com.google.android.gms.tasks.OnCanceledListener
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception
import java.util.*
import kotlin.collections.HashMap

class FirebaseFirestore : AppCompatActivity() {

    lateinit var mTitle: TextInputEditText
    lateinit var mDesc : TextInputEditText
    lateinit var mSubmit : Button
    lateinit var mShow : Button
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_firestore2)

        mTitle = findViewById(R.id.et_title)
        mDesc = findViewById(R.id.et_description)
        mSubmit = findViewById(R.id.btn_save)
        mShow = findViewById(R.id.btn_show)

        val actionBar = supportActionBar
        actionBar!!.setTitle("Add data")

        mSubmit.setOnClickListener {
            val title = mTitle.text.toString().trim()
            val description = mDesc.text.toString().trim()

            saveData(title,description)
        }

        mShow.setOnClickListener {
            startActivity(Intent(this,DisplayListActivity::class.java))
        }

    }

    private fun saveData(title: String, description: String) {

        val id = UUID.randomUUID()

        val hashMap = HashMap<String,String>()

        hashMap.put("id",id.toString())
        hashMap.put("title",title)
        hashMap.put("desc",description)

        db.collection("Document").document(id.toString())
            .set(hashMap)
            .addOnCompleteListener(object : OnCompleteListener<Void> {
                override fun onComplete(p0: Task<Void>) {
                     Toast.makeText(this@FirebaseFirestore,"Save successfully",Toast.LENGTH_LONG).show()
                }

            }).addOnFailureListener(object : OnFailureListener{
                override fun onFailure(p0: Exception) {
                    Toast.makeText(this@FirebaseFirestore,"Error $p0",Toast.LENGTH_LONG).show()
                }

            })



    }
}
