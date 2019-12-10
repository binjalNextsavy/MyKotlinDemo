package com.example.myapplication.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.R

class VollyDActivity : AppCompatActivity() {
    private var requestQueue: RequestQueue? = null
    private var stringRequest: StringRequest? = null
    var vollyButton: Button? = null
    private val url = "http://www.mocky.io/v2/597c41390f0000d002f4dbd1"
    // private String url = "https://www.unimedliving.com/api/v2/audios";
//private String url = "https://www.unimedliving.com/api/v2/articles";
//private String url = "https://api.androidhive.info/contacts/";
//private String url = "https://www.unimedliving.com/api/v2/videos";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volly_d)
        vollyButton = findViewById(R.id.vollyButton)
        vollyButton!!.setOnClickListener(View.OnClickListener { sendAndRequestResponse() })
    }

    private fun sendAndRequestResponse() {
        requestQueue = Volley.newRequestQueue(this)
        stringRequest = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener { response ->
                Toast.makeText(this@VollyDActivity, "Response :  $response", Toast.LENGTH_SHORT).show()
                Log.e("AAA", "Reps$response")
            },
            Response.ErrorListener { error ->
                Toast.makeText(this@VollyDActivity, "Error :  $error", Toast.LENGTH_SHORT).show()
            })
        requestQueue!!.add(stringRequest)
    }
}