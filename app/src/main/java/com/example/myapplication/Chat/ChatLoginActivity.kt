package com.example.myapplication.Chat

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.textclassifier.TextLinks
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
import org.json.JSONObject
import java.lang.Exception
import kotlin.math.log

class ChatLoginActivity : AppCompatActivity() {
    lateinit var registerUser: TextView
    lateinit var userName: EditText
    lateinit var userPassword: EditText
    lateinit var loginButton: Button
    lateinit var user: String
    lateinit var password: String
    private lateinit var stringRequest: StringRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_login)

        registerUser = findViewById(R.id.register)
        userName = findViewById(R.id.username)
        userPassword = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)

        registerUser.setOnClickListener {
            val intent = Intent(this, ChatRegisterActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            user = userName.text.toString()
            password = userPassword.text.toString()

            if (user == "") {
                userName.error = "Field Cannot be empty"
            } else if (password == "") {
                userPassword.error = "Field Cannot be empty"
            } else {
                val url = "https://mykotlindemo.firebaseio.com/user.json"
                val prgressDialog = ProgressDialog(this)
                prgressDialog.setMessage("Loading..")
                prgressDialog.show()

                stringRequest = StringRequest(Request.Method.GET, url, Response.Listener { response ->
                    Toast.makeText(this, "Response :  $response", Toast.LENGTH_SHORT).show()
                    Log.e("AAA", "Reps$response")

                    try {
                        val jsonObject = JSONObject(response)

                        if (!jsonObject.has(user)) {
                            Toast.makeText(this, "User Not Found", Toast.LENGTH_LONG).show()
                        } else if (jsonObject.getJSONObject(user).getString("password") == password) {
                            ChatUserDetail.userName = user
                            ChatUserDetail.userPassword = password
                            
                            startActivity(Intent(this, ChatUserActivity::class.java))
                        } else {
                            Toast.makeText(this, "Incorrect password", Toast.LENGTH_LONG).show()
                        }


                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    prgressDialog.dismiss()

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

