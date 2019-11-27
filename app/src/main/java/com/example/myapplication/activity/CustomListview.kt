package com.example.myapplication.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.example.myapplication.adapter.MyListAdapter
import com.example.myapplication.R
import com.google.android.material.snackbar.Snackbar

class CustomListview : AppCompatActivity() {

    var title = arrayOf<String>("ABC","EFG","HIJ","PQR","XYZ")

    var description = arrayOf("ABC is ABC is ABC is ABC is ABC is ","EFG is EFG is EFG is EFG is ","HIJ is HIJ is HIJ is","PQR is PQR is PQR is","XYz is")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_listview)

        val myListAdapter = MyListAdapter(this, title, description)

        val listview : ListView = findViewById(R.id.listDetail)

        listview.adapter = myListAdapter

        listview.setOnItemClickListener { parent, view, position, id ->

            val selectedItem = parent.getItemAtPosition(position)
            val selectedPosition = parent.getItemIdAtPosition(position)

            Snackbar.make(view,"Selected Item is $selectedItem & Position is $selectedPosition",Snackbar.LENGTH_LONG).show()
        }

        findViewById<Button>(R.id.showWeb).setOnClickListener {
            val webview : WebView = findViewById(R.id.webview)

            val webSettings = webview.settings
            webSettings.javaScriptEnabled = true

            webview.webViewClient = MyWebClient(this)
            webview.loadUrl("https://www.google.com/")
        }

    }
}

class MyWebClient internal constructor(private val context: Activity) : WebViewClient(){

    override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
        view?.loadUrl(url)
        return true
    }

    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {

        Toast.makeText(context, "Got Error! $error", Toast.LENGTH_SHORT).show()
    }
}
