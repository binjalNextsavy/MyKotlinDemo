package com.example.myapplication.Chat

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.R
import org.json.JSONObject
import java.util.*


class ChatUserActivity : AppCompatActivity() {

        lateinit var userList : ListView
        lateinit var noUserText:TextView
        val al : ArrayList<String> = ArrayList()

        var totalUser = 0
        lateinit var prgressDialog:ProgressDialog

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_user)

            userList = findViewById(R.id.usersList)
            noUserText = findViewById(R.id.noUsersText)

            prgressDialog = ProgressDialog(this)
            prgressDialog.setMessage("Loading..")
            prgressDialog.show()

            val url = "https://mykotlindemo.firebaseio.com/user.json"

            val stringRequest = StringRequest(
                Request.Method.GET, url, Response.Listener { response ->
                    //Toast.makeText(this, "Response :  $response", Toast.LENGTH_SHORT).show()
                    Log.e("AAA", "Reps$response")

                    doOnSuccess(response)
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this, "Error :  $error", Toast.LENGTH_SHORT).show()
                })

            stringRequest.setRetryPolicy(
                DefaultRetryPolicy(
                    5000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
            )

            val queue: RequestQueue = Volley.newRequestQueue(this)
            queue.add(stringRequest)

            userList.setOnItemClickListener { parent, view, position, id ->
                ChatUserDetail.chatWith = al!!.get(position)
                startActivity(Intent(this,ChatMainActivity::class.java))
            }
        }

    private fun doOnSuccess(response: String?) {
            try {
                val jsonObject = JSONObject(response!!)
                val i = jsonObject.keys()
                var keys: String

                while (i.hasNext()) {
                    keys = i.next().toString()

                    if (!keys.equals(ChatUserDetail.userName)){
                        al!!.add(keys)
                    }
                    totalUser++
                }
            } catch (e:Exception){
                e.printStackTrace()
            }

            if (totalUser<=1){
                noUserText.isVisible = true
                userList.isVisible = false
            } else {
                noUserText.isVisible = false
                userList.isVisible = true

                userList.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,al!!)

            }
        prgressDialog.dismiss()
    }
}
