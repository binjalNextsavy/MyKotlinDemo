package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.Chat.ChatRegisterActivity
import com.example.myapplication.Chat.ChatUserActivity
import com.example.myapplication.R
import com.example.myapplication.fragment.HelpFragment
import com.example.myapplication.sqlitdemo.UserRegisterActivity
import com.firebase.client.Firebase
import com.google.firebase.auth.FirebaseAuth


class SplashScreen : AppCompatActivity() {
    lateinit var btn: Button
    lateinit var btn2: Button
    lateinit var btn4: Button
    var btn3: Button? = null
    lateinit var toolbar: Toolbar
    lateinit var ll : LinearLayout
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        btn = findViewById(R.id.splashbtn)
        btn2 = findViewById(R.id.firebasebtn)
        btn3 = findViewById(R.id.sqlitebtn)
        btn4 = findViewById(R.id.btnchat)
        toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle("SplashScreen")

        mAuth = FirebaseAuth.getInstance()

        ll = findViewById(R.id.dynamiclinear)

        btn.setOnClickListener(View.OnClickListener {
            if (FirebaseAuth.getInstance().currentUser == null) {
                val i = Intent(this@SplashScreen, FirebasePhoneAuth::class.java)
                startActivity(i)
            } else {
                val i = Intent(this@SplashScreen, HomeScreen::class.java)
                startActivity(i)
            }
        })
        btn2.setOnClickListener(View.OnClickListener {
            val i = Intent(this@SplashScreen, FirebaseFirestoreDemo::class.java)
            startActivity(i)
        })
        btn3!!.setOnClickListener {
            val i = Intent(this@SplashScreen, UserRegisterActivity::class.java)
            startActivity(i)
        }

        btn4!!.setOnClickListener {
            val i = Intent(this@SplashScreen, ChatRegisterActivity::class.java)
            startActivity(i)
/*
            var user = mAuth.currentUser
            if (mAuth.currentUser != null){
                val i = Intent(this@SplashScreen, ChatUserActivity::class.java)
                startActivity(i)
            } else {
                val i = Intent(this@SplashScreen, ChatRegisterActivity::class.java)
                startActivity(i)
            }
*/

        }

        //add button dynamically
        val btn_dynamic = Button(this)
        btn_dynamic.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        btn_dynamic.setText("Dynamic Button")

        ll.addView(btn_dynamic)

        btn_dynamic.setOnClickListener {

            /*val intent = Intent(this,HelpFragment ::class.java)
            startActivity(intent)*/
           /* val fragment: android.app.Fragment = HelpFragment()
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()
            transaction.replace(
                R.id.fragment_container,fragment
            ) // fragmen container id in first parameter is the  container(Main layout id) of Activity

            transaction.addToBackStack(null) // this will manage backstack

            transaction.commit()*/

            if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, HelpFragment())
                    .commit()
            }

        }



           /*//share any
//            val sharingIntent = Intent(Intent.ACTION_SEND)
//            sharingIntent.type = "text/plain"
//            val shareBody =
//                "Hey, Checkout how I keep myself updated with all the startup News using Start-O-Preneur!\n" + "https://www.google.com/"
//            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Your startup update")
//            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
//            startActivity(Intent.createChooser(sharingIntent, "Share via"))

            //Twitter Link
            //val browserIntent = Intent(Intent.ACTION_VIEW,Uri.parse("https://www.google.com/"))
           // startActivity(browserIntent)


            //Facebook Link
           // val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/H7SRmT"))
            //startActivity(browserIntent)

            val emailIntent = Intent(Intent.ACTION_SEND, Uri.parse("mailto:"))

            emailIntent.type = "message/rfc822"

            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("binjalprajapati07@gmail.com"))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Inquiry/Feedback")

            try { // the user can choose the email client
                startActivity(Intent.createChooser(emailIntent, "Choose an email client from..."))
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(
                    this, "No email client configured!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }*/
    }
}