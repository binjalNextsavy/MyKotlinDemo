package com.example.myapplication.forJson

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class DemoForJson : AppCompatActivity() {

    var listView: ListView? = null
    private var pDialoag: ProgressDialog? = null
    var contactList: ArrayList<HashMap<String, String?>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_for_json)
        contactList = ArrayList()
        listView = findViewById(R.id.jsonList)
        GetContacts().execute()
    }

    private inner class GetContacts : AsyncTask<Void?, Void?, Void?>() {
        override fun onPreExecute() {
            super.onPreExecute()
            pDialoag = ProgressDialog(this@DemoForJson)
            pDialoag!!.setMessage("Please Wait...")
            pDialoag!!.setCancelable(false)
            pDialoag!!.show()
        }

        override fun doInBackground(vararg params: Void?): Void? {
            val httpHandler = HttpHandler()

            val jsonStr = httpHandler.makeServiceCall(url)

            try {
                val jsonArray = JSONArray(jsonStr)
                for (i in 0 until jsonArray.length()) {

                    val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                    val str_id = jsonObject.getString("id")
                    val str_title = jsonObject.getString("title")
                    val str_content_html = jsonObject.getString("content_html")
                    val str_published_at = jsonObject.getString("published_at")
                    val str_summary = jsonObject.getString("summary")
                    //val str_image = jsonObject.getString("image")


                    val str_image = jsonObject.getJSONObject("image")
                    val url = str_image.getString("url")
                    Log.e("TAG!!", "url$url")

                    val thum_big = str_image.getJSONObject("thumb_big")
                    val  t_big = thum_big.getString("url")
                    Log.e("TAG!!", "big$t_big")


                    val thum_mid = str_image.getJSONObject("thumb_medium")
                    val  tm_mid = thum_mid.getString("url")
                    Log.e("TAG!!", "big$tm_mid")

                    val thum_small = str_image.getJSONObject("thumb_small")
                    val  tm_small = thum_small.getString("url")
                    Log.e("TAG!!", "big$tm_small")


                    val contact = HashMap<String, String?>()
                    contact["id"] = str_id
                    contact["name"] = str_title
                    contact["email"] = str_content_html
                    contact["mobile"] = str_published_at
                    contactList!!.add(contact)


                }
            } catch (e: Exception) {
                e.printStackTrace()
            }


            /*if (jsonStr != null) {
                try {
                    val jsonObject = JSONObject(jsonStr)
                    val contacts = jsonObject.getJSONArray("contacts")
                    for (i in 0 until contacts.length()) {
                        val c = contacts.getJSONObject(i)
                        val id = c.getString("id")
                        val name = c.getString("name")
                        val email = c.getString("email")
                        val address = c.getString("address")
                        val gender = c.getString("gender")

                        val phone = c.getJSONObject("phone")

                        val mobile = phone.getString("mobile")
                        val home = phone.getString("home")
                        val office = phone.getString("office")
                        val contact = HashMap<String, String?>()
                        contact["id"] = id
                        contact["name"] = name
                        contact["email"] = email
                        contact["mobile"] = mobile
                        contactList!!.add(contact)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            } else {
                runOnUiThread { Toast.makeText(this@DemoForJson, "error", Toast.LENGTH_SHORT).show() }
            }*/
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            if (pDialoag!!.isShowing) {
                pDialoag!!.dismiss()
                val adapter: ListAdapter = SimpleAdapter(
                    this@DemoForJson, contactList,
                    R.layout.list_item,
                    arrayOf(
                        "name",
                        "email",
                        "mobile"
                    ), intArrayOf(
                        R.id.name,
                        R.id.email,
                        R.id.mobile
                    )
                )
                listView!!.adapter = adapter
            }
        }


    }

    companion object {
        //private const val url = "https://api.androidhive.info/contacts/"
        private const val url = "https://www.unimedliving.com/api/v2/articles"
        //private const val url = "https://www.unimedliving.com/api/v2/audios"
    }
}