package com.example.myapplication.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.myapplication.BuildConfig
import com.example.myapplication.R
import java.io.File
import java.io.IOException

class DemoForCamera : AppCompatActivity() {

    lateinit var btnCamera: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_for_camrea)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "Camera"

        // Here, we are making a folder named picFolder to store
        // pics taken by the camera using this application.

        val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/picFolder/";

        val newdtr = File(dir)
        newdtr.mkdirs()
        btnCamera = findViewById(R.id.btn_camera)
        btnCamera.setOnClickListener {

            if (checkPermission()) {

                // Here, the counter will be incremented each time, and the
                // picture taken by camera will be stored as 1.jpg,2.jpg and likewise.
                count = count++
                val file = "$dir$count.jpg"
                val newFile = File(file)
                try {
                    newFile.createNewFile()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                //Uri outputFileUri = Uri.fromFile(newFile);
                val outputFileUri = FileProvider.getUriForFile(
                    this@DemoForCamera,
                    BuildConfig.APPLICATION_ID + ".provider",
                    newFile
                )
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri)
                startActivityForResult(cameraIntent, PERMISSION_REQUEST_CODE)
            } else {
                requestPermission()
            }
        }
    }

    private fun checkPermission(): Boolean {
        return !(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
            this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this,
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            PERMISSION_REQUEST_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PERMISSION_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "Pic saved", Toast.LENGTH_SHORT).show()
            Log.e("Camera", "Pic saved")
        }
    }





    companion object {
        private const val PERMISSION_REQUEST_CODE = 123
        var count = 0
    }
}