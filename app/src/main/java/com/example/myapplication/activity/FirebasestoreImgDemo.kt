package com.example.myapplication.activity

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.myapplication.R
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.*
import java.io.File
import java.lang.Exception

class FirebasestoreImgDemo : AppCompatActivity() {

    lateinit var upload : Button
    lateinit var choose : Button

    lateinit var imgStore : ImageView

    lateinit var storageReference: StorageReference

    lateinit var filePath : Uri
     var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebasestore_img_demo)

        storageReference = FirebaseStorage.getInstance().reference

        upload = findViewById(R.id.buttonUpload)
        choose = findViewById(R.id.buttonChoose)
        imgStore = findViewById(R.id.imgImgStore)

        choose.setOnClickListener {
            showFileChooser()
        }


        upload.setOnClickListener {

            //imgStore.setImageBitmap(null)
            val progressDialog : ProgressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading")
            progressDialog.show()

            val storeImageRef = storageReference.child("images/pic$count.jpg")
            count++

            storeImageRef.putFile(filePath)
                .addOnSuccessListener {
                    OnSuccessListener<UploadTask.TaskSnapshot> {
                        progressDialog.dismiss()
                        Toast.makeText(this@FirebasestoreImgDemo,"Image Upload",Toast.LENGTH_LONG).show()
                    }
                }.addOnFailureListener { p0 ->
                    progressDialog.dismiss()
                    Toast.makeText(this@FirebasestoreImgDemo,""+p0.message,Toast.LENGTH_LONG).show()
                }.addOnProgressListener {
                    OnProgressListener<UploadTask.TaskSnapshot> { p0 ->
                        val progress = (100.0 * p0.bytesTransferred)/p0.totalByteCount

                        progressDialog.setMessage("Uploaded$progress%...")
                    }
                }

           /* //Forecefully dismiss
            Handler().postDelayed({
                progressDialog.dismiss()
            },10000)*/
        }

        findViewById<Button>(R.id.buttondownload).setOnClickListener {
            val storeImageRef = storageReference.child("images/pic0.jpg")
            var localFile: File = File.createTempFile("images","jpg")

            storeImageRef.getFile(localFile)
                .addOnSuccessListener { OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    Toast.makeText(this,"Download successfullly",Toast.LENGTH_LONG).show()
                } }.addOnFailureListener(OnFailureListener {
                    Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show()
                })
            /*
            val pathReference : StorageReference = storageReference.child("images/pic.jpg")
            storageReference.child("images/pic.jpg").downloadUrl.addOnSuccessListener {
                Toast.makeText(this,"Download successfullly",Toast.LENGTH_LONG).show()

            }.addOnFailureListener(OnFailureListener() {
                Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show()
            })*/
            }
        }


    private fun showFileChooser() {
        intent = Intent();
        intent.type = "image/*";
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"select Picture"), PICK_IMG_REQ)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMG_REQ && resultCode == Activity.RESULT_OK && data != null && data.data != null){
            filePath = data.data!!
            try {
                val bitmap : Bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filePath)
                imgStore.setImageBitmap(bitmap)
            } catch (e: Exception){
                Toast.makeText(this,"" + e.toString(),Toast.LENGTH_LONG).show()
            }
        }
    }
    companion object{
        private const val PICK_IMG_REQ = 321

    }
}
