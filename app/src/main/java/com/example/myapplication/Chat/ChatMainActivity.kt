package com.example.myapplication.Chat

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.marginBottom
import com.example.myapplication.R
import com.firebase.client.ChildEventListener
import com.firebase.client.DataSnapshot
import com.firebase.client.Firebase
import com.firebase.client.FirebaseError
import kotlinx.android.synthetic.main.message_area.*
import org.w3c.dom.Text

class ChatMainActivity : AppCompatActivity() {

    lateinit var layout: LinearLayout
    lateinit var layout2: RelativeLayout
    lateinit var sendButton: ImageView
    lateinit var scrollView: ScrollView
    lateinit var reference1: Firebase
    lateinit var reference2: Firebase
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_main)

        toolbar = findViewById(R.id.toolbar)

        layout = findViewById(R.id.layout1)
        layout2 = findViewById(R.id.layout2)

        sendButton = findViewById(R.id.sendButton)
        scrollView = findViewById(R.id.scrollView)

        Firebase.setAndroidContext(this)

        reference1 = Firebase("https://mykotlindemo.firebaseio.com/message" + "_" + ChatUserDetail.userName + "_" + ChatUserDetail.chatWith)
        reference2 = Firebase("https://mykotlindemo.firebaseio.com/message" + "_" + ChatUserDetail.chatWith + "_" + ChatUserDetail.userName)

        sendButton.setOnClickListener {
            val messageText = messageArea.text.toString()

            if (!messageText.equals("")) {
                val map = HashMap<String, String>()

                map["message"] = messageText
                map["user"] = ChatUserDetail.userName

                reference1.push().setValue(map)
                reference2.push().setValue(map)

                messageArea.setText("")
            }
        }

        reference1.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(p0: DataSnapshot?, p1: String?) {

                val map = p0!!.getValue(Map::class.java)
                val message: String = map["message"].toString()
                val userName: String = map["user"].toString()

                if (userName.equals(ChatUserDetail.userName)) {

                    addMessageBox("You:-\n$message", 1)
                } else {
                    addMessageBox(ChatUserDetail.chatWith.toString() + ":-\n" + message, 2)
                    toolbar.title = ChatUserDetail.chatWith
                }

            }

            override fun onCancelled(p0: FirebaseError?) {

            }

            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {

            }

            override fun onChildChanged(p0: DataSnapshot?, p1: String?) {

            }


            override fun onChildRemoved(p0: DataSnapshot?) {

            }
        })
    }

    private fun addMessageBox(s: String, i: Int) {
        val textView = TextView(this)
        textView.setText(s)

        val lp2 = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        lp2.weight = 1.0f
        lp2.setMargins(4,4,4,4)


        if (i == 1) {
            lp2.gravity = Gravity.RIGHT
            textView.setBackgroundResource(R.drawable.bubble_out_msg)
            textView.setPadding(20,12,40,12)


            textView.textSize = 13.5f
        } else {
            lp2.gravity = Gravity.LEFT
            textView.setBackgroundResource(R.drawable.bubble_in_msg)
            textView.setPadding(40,12,20,12)
            textView.textSize = 13.5f
        }

        textView.layoutParams = lp2
        layout.addView(textView)
        scrollView.fullScroll(View.FOCUS_DOWN)





        /*if (i == 1) {

            setContentView(R.layout.chat_textview)

            var linearLayout = findViewById<LinearLayout>(R.id.chat_ll)
            var textView = findViewById<TextView>(R.id.text_message_incoming)

            linearLayout.gravity = Gravity.RIGHT
            textView.setBackgroundResource(R.drawable.bubble_out_msg)
        } else {
            setContentView(R.layout.chat_textview)

            var linearLayout = findViewById<LinearLayout>(R.id.chat_ll)
            var textView = findViewById<TextView>(R.id.text_message_incoming)

            linearLayout.gravity = Gravity.LEFT
            textView.setBackgroundResource(R.drawable.bubble_in_msg)
        }*/
    }

}

