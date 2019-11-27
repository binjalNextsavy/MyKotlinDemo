package com.example.myapplication.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class DemoWhatsapp : AppCompatActivity() {

    private var pictureCode = 101
   // lateinit var textField: TextInputEditText
    lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_whatsapp)

        //textField = findViewById(R.id.whapsapp_text) as TextInputEditText
        val actionBar = supportActionBar

        //actionBar.title = "Whatsapp"

        btn = findViewById(R.id.whatsapp_btn)
        btn.setOnClickListener {
            /* val imgUri = Uri.parse(imageView.toString())
            val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.type = "text/plain"
            whatsappIntent.setPackage("com.whatsapp")
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, "The text you wanted to share")
            whatsappIntent.putExtra(Intent.EXTRA_STREAM, imgUri)
            whatsappIntent.type = "image/jpeg"
            whatsappIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(whatsappIntent)*/

  //          val text1 = textField.text.toString()
//
//            if (!text1.isEmpty()){
//                shareData(text1)
//            }

            /*val textToshare = "Hello"
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, textToshare)
            sendIntent.type = "text/plain"
            startActivity(sendIntent)*/

            selectImage()
        }


    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,getString(R.string.select_picture)), pictureCode)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null && resultCode == Activity.RESULT_OK && (requestCode == pictureCode)) {
            startShareImage(getString(R.string.sharing_image), data.data)
        }

    }



    private fun startShareImage(s: String, data: Uri?) {
        val sendIntent = Intent(Intent.ACTION_SEND)
        sendIntent.type = "images/*"
        sendIntent.putExtra(Intent.EXTRA_TEXT,s)
        sendIntent.putExtra(Intent.EXTRA_STREAM,data)
        startActivity(Intent.createChooser(sendIntent,getString(R.string.choose_photo)))
    }

    //    private fun shareData(s: String) {
//        val sendIntent = Intent()
//        sendIntent.action = Intent.ACTION_SEND
//        sendIntent.putExtra(Intent.EXTRA_TEXT,s)
//        sendIntent.type = "text/plain"
//        startActivity(sendIntent)
//    }



}
