package com.example.myapplication.activity

/*import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.R
import com.example.toastylibrary.FancyToast

class keyboardLibrary : AppCompatActivity() ,View.OnClickListener{

    lateinit var b1 : Button
    lateinit var b2 : Button
    lateinit var b3 : Button
    lateinit var b4 : Button
    lateinit var b5 : Button
    lateinit var b6 : Button
    lateinit var b7 : Button
    lateinit var b8 : Button
    lateinit var b9 : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keyboard_library)

        b1 = findViewById(R.id.button2)
        b2 = findViewById(R.id.button3)
        b3 = findViewById(R.id.button4)
        b4 = findViewById(R.id.button5)
        b5 = findViewById(R.id.button6)
        b6 = findViewById(R.id.button7)
        b7 = findViewById(R.id.button8)
        b8 = findViewById(R.id.button9)
        b9 = findViewById(R.id.button10)

        b1.setOnClickListener(this)
        b2.setOnClickListener(this)
        b3.setOnClickListener(this)
        b4.setOnClickListener(this)
        b5.setOnClickListener(this)
        b6.setOnClickListener(this)
        b7.setOnClickListener(this)
        b8.setOnClickListener(this)
        b9.setOnClickListener(this)



    }
    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.button2 -> {

                FancyToast.makeText(this, "This is Default Toast", Toast.LENGTH_LONG, FancyToast.default, true)!!.show();
            }
            R.id.button3 -> {
                FancyToast.makeText(this, "Success Toast !", Toast.LENGTH_LONG,
                    FancyToast.success, true)!!.show();
            }
            R.id.button4 -> {
                FancyToast.makeText(this, "This is an Error Toast", Toast.LENGTH_LONG, FancyToast.error, true)!!.show();
            }
            R.id.button5 -> {
                FancyToast.makeText(this, "Beware of dog", Toast.LENGTH_LONG, FancyToast.warning, true)!!.show();
            }
            R.id.button6 -> {
                FancyToast.makeText(this, "Here is some Info for you", Toast.LENGTH_LONG, FancyToast.info, true)!!.show();
            }
            R.id.button7 -> {
                FancyToast.makeText(this, "This is Confusing Toast", Toast.LENGTH_LONG, FancyToast.confusing, false)!!.show();

            }
            R.id.button8 -> {
                FancyToast.makeText(this, "This is Custom Toast", Toast.LENGTH_LONG, FancyToast.confusing, R.drawable.ic_phone_android_black_24dp, true)!!.show();
            }
            R.id.button9 -> {
                FancyToast.makeText(this, "This is Custom Toast with no android icon",
                    Toast.LENGTH_LONG, FancyToast.confusing, R.drawable.ic_phone_android_black_24dp, false)!!.show();
            }
            R.id.button10 -> {
                FancyToast.makeText(this, "This is a Success Toast", Toast.LENGTH_LONG, FancyToast.success, false)!!.show();
            }

        }

    }
    }*/

