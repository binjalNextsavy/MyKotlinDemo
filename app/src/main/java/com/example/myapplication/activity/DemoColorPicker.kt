package com.example.myapplication.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.SeekBar
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_demo_color_picker.*
import kotlinx.android.synthetic.main.colorpicker.*

class DemoColorPicker : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_color_picker)

        btnColorPicker.setOnClickListener {
            colorSelector.visibility = View.VISIBLE
        }

        btnColorSelected.setOnClickListener {
            colorSelector.visibility = View.VISIBLE
        }

        strColor.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s!!.length == 6){
                    colorA.progress = 255
                    colorR.progress = Integer.parseInt(s.substring(0..1), 16)
                    colorG.progress = Integer.parseInt(s.substring(2..3), 16)
                    colorB.progress = Integer.parseInt(s.substring(4..5), 16)
                } else if (s.length == 8){
                    colorA.progress = Integer.parseInt(s.substring(0..1), 16)
                    colorR.progress = Integer.parseInt(s.substring(2..3), 16)
                    colorG.progress = Integer.parseInt(s.substring(4..5), 16)
                    colorB.progress = Integer.parseInt(s.substring(6..7), 16)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        colorA.max = 255
        colorA.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val colorStr = getColorString()
                strColor.setText(colorStr!!.replace("#","").toUpperCase())
                btnColorPreview.setBackgroundColor(Color.parseColor(colorStr))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        colorR.max = 255
        colorR.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val colorStr = getColorString()
                strColor.setText(colorStr!!.replace("#","").toUpperCase())
                btnColorPreview.setBackgroundColor(Color.parseColor(colorStr))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        colorG.max = 255
        colorG.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val colorStr = getColorString()
                strColor.setText(colorStr!!.replace("#","").toUpperCase())
                btnColorPreview.setBackgroundColor(Color.parseColor(colorStr))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        colorB.max = 255
        colorB.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val coloStr = getColorString()
                strColor.setText(coloStr!!.replace("#","").toUpperCase())
                btnColorPreview.setBackgroundColor(Color.parseColor(coloStr))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        colorCancelBtn.setOnClickListener {
            colorSelector.visibility = View.GONE
        }

        colorOkBtn.setOnClickListener {
            val colorString = getColorString()
            btnColorSelected.setBackgroundColor(Color.parseColor(colorString))
            colorSelector.visibility = View.GONE
        }
    }

    private fun getColorString(): String? {
        Log.e("Color","A"+colorA.progress)
        var a = Integer.toHexString(((255*colorA.progress)/colorA.max))
        if(a.length==1) a = "0"+a
        Log.e("Color","R"+colorR.progress)
        var r = Integer.toHexString(((255*colorR.progress)/colorR.max))
        if(r.length==1) r = "0"+r
        Log.e("Color","G"+colorG.progress)
        var g = Integer.toHexString(((255*colorG.progress)/colorG.max))
        if(g.length==1) g = "0"+g
        Log.e("Color","B"+colorB.progress)
        var b = Integer.toHexString(((255*colorB.progress)/colorB.max))
        if(b.length==1) b = "0"+b
        return "#" + a + r + g + b
    }
}
