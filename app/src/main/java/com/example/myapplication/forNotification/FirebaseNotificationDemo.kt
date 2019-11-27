package com.example.myapplication.forNotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.R
import com.google.firebase.messaging.FirebaseMessaging
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class FirebaseNotificationDemo : AppCompatActivity() {

    lateinit var msg: EditText
    private val FCM_API = "https://fcm.googleapis.com/fcm/send"
    private val serverKey =
        "key=" + "AAAAJ5qMzuE:APA91bF_YxYQhMjMvujw4eCRtWlVtIxbeIM27f03UNw5Mjnp4UtfmmP5HHJ9NC0LbIPPdEiEnX4tapRpBSBlzF96W4qem1qT7cP3nsbYpjhl0ls2ddVwtA14cSBfKSFAQNH9AF785F3o"
    private val contentType = "application/json"

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(this.applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_notification_demo)

        msg = findViewById(R.id.msg)

        FirebaseMessaging.getInstance().subscribeToTopic("/topics/Notification")

        findViewById<Button>(R.id.submit).setOnClickListener {
            if (!TextUtils.isEmpty(msg.text)) {
                val topic = "/topics/Notification"

                val notification = JSONObject()
                val notificationBody = JSONObject()

                try {
                    notificationBody.put("title", "Firebase Notification")
                    notificationBody.put("message", msg.text)
                    notification.put("to", topic)
                    notification.put("data", notificationBody)
                } catch (e: JSONException) {
                    Log.e("TAG", "Error" + e.message)
                }

                sendNotification(notification)



            }

        }
    }

    private fun sendNotification(notification: JSONObject) {
        val jsonObjectRequest = object : JsonObjectRequest(FCM_API,notification,
            Response.Listener {
                msg.setText("")
        }, Response.ErrorListener {
                Toast.makeText(this,"Error Listner",Toast.LENGTH_LONG).show()
                Log.e("TAG","Error Listner")
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["Authorization"] = serverKey
                params["Content-Type"] = contentType
                return params
            }
        }

        requestQueue.add(jsonObjectRequest)
    }
}







