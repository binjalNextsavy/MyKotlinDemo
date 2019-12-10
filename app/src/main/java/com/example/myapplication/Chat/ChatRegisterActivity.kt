package com.example.myapplication.Chat

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.R
import com.firebase.client.Firebase
import org.json.JSONObject
import java.lang.Exception

class ChatRegisterActivity : AppCompatActivity() {
    lateinit var loginUser: TextView
    lateinit var userName: EditText
    lateinit var userPassword: EditText
    lateinit var regButton: Button
    lateinit var user: String
    lateinit var password: String
    private lateinit var stringRequest: StringRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_register)


        userName = findViewById(R.id.username)
        userPassword = findViewById(R.id.password)
        regButton = findViewById(R.id.registerButton)
        loginUser = findViewById(R.id.login)

        Firebase.setAndroidContext(this)

        loginUser.setOnClickListener {
            startActivity(Intent(this,ChatLoginActivity::class.java))
        }

        regButton.setOnClickListener {
            user = userName.text.toString()
            password = userPassword.text.toString()

            if (user == "") {
                userName.error = "Field Cannot be empty"
            } else if (password == "") {
                userPassword.error = "Field Cannot be empty"
            } else {
                val progressDialog = ProgressDialog(this)
                progressDialog.setMessage("Loading..")
                progressDialog.show()

                val url = "https://mykotlindemo.firebaseio.com/user.json"


                stringRequest = StringRequest(
                    Request.Method.GET, url, Response.Listener { response ->
                        //Toast.makeText(this, "Response :  $response", Toast.LENGTH_SHORT).show()
                        Log.e("AAA", "Reps$response")

                        val reference = Firebase("https://mykotlindemo.firebaseio.com/user")

                        if (response.equals("null")){
                            reference.child(user).child("password").setValue(password)
                            Toast.makeText(this,"Registration successfully",Toast.LENGTH_LONG).show()
                        } else {
                            try {
                                val jsonObject = JSONObject(response)

                                if (!jsonObject.has(user)) {
                                    reference.child(user).child("password").setValue(password)
                                    Toast.makeText(this,"Registration successfully",Toast.LENGTH_LONG).show()
                                }  else {
                                    Toast.makeText(this,"username already exists",Toast.LENGTH_LONG).show()
                                }

                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                        progressDialog.dismiss()

                    },
                    Response.ErrorListener { error ->
                        Toast.makeText(this, "Error :  $error", Toast.LENGTH_SHORT).show()
                    })

                val queue: RequestQueue = Volley.newRequestQueue(this)
                queue.add(stringRequest)
            }
        }
    }
}
