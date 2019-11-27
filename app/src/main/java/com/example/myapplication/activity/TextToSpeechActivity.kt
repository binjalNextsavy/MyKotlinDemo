package com.example.myapplication.activity

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.R
import java.util.*

class TextToSpeechActivity : AppCompatActivity(),TextToSpeech.OnInitListener {

    lateinit var tts: TextToSpeech
    lateinit var btnSpeak: Button
    lateinit var etInput: EditText
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_to_speech)

        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "TextToSpeech"
        etInput = findViewById(R.id.etInput)
        btnSpeak = findViewById(R.id.btnSpeak)

        btnSpeak.isEnabled = false

        tts = TextToSpeech(this,this)

        btnSpeak.setOnClickListener {
            speakOut()
        }

        etInput.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                speakOut()
            }
        })
    }

    override fun onInit(status: Int){

        if (status == TextToSpeech.SUCCESS){
            val result = tts.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this,"Language specified is not supported",Toast.LENGTH_LONG).show()
            } else{
                btnSpeak.isEnabled = true
            }
        } else {
            Log.e("TextSpeech","Initialization is failed")
        }

    }
    private fun speakOut() {
        val textFor = etInput.text.toString()
        tts.speak(textFor,TextToSpeech.QUEUE_FLUSH, null,"")
    }

    override fun onDestroy() {
        if (tts != null){
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }
}
