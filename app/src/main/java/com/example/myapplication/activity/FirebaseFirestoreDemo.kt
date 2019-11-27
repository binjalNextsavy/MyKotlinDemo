package com.example.myapplication.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseFirestoreDemo : AppCompatActivity() {

    lateinit var db : DocumentReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_firestore)

        db = FirebaseFirestore.getInstance().document("IceCreams/Flavours")

        val store = findViewById<Button>(R.id.storeBtn)

        store.setOnClickListener {
            view : View? -> store ()
        }
    }

    private fun store () {

        val flavourTxt = findViewById<View>(R.id.flavourTxt) as EditText
        val ingrediant1Txt = findViewById<View>(R.id.collectionsTxt) as EditText
        val ingrediant2Txt = findViewById<View>(R.id.in2Txt) as EditText
        val ingrediant3Txt = findViewById<View>(R.id.in3Txt) as EditText

        var flavour = flavourTxt.text.toString().trim()
        val ingrediant1 = ingrediant1Txt.text.toString().trim()
        val ingrediant2 = ingrediant2Txt.text.toString().trim()
        val ingrediant3 = ingrediant3Txt.text.toString().trim()

        if (!flavour.isEmpty() && !ingrediant1.isEmpty() && !ingrediant2.isEmpty() && !ingrediant3.isEmpty()) {
            try {
                val items = HashMap<String, Any>()
                items.put("Ingrediant-1", ingrediant1)
                items.put("Ingrediant-2", ingrediant2)
                items.put("Ingrediant-3", ingrediant3)
                db.collection(flavour).document("Ingrediants").set(items).addOnSuccessListener {
                        void: Void? -> Toast.makeText(this, "Successfully uploaded to the database :)", Toast.LENGTH_LONG).show()
                }.addOnFailureListener {
                        exception: java.lang.Exception -> Toast.makeText(this, exception.toString(), Toast.LENGTH_LONG).show()
                }
            }catch (e:Exception) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
            }
        }else {
            Toast.makeText(this, "Please fill up the fields :(", Toast.LENGTH_LONG).show()
        }
    }
}


